package com.sparta.dockingfinalproject.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.dockingfinalproject.common.SuccessResult;
import com.sparta.dockingfinalproject.education.Education;
import com.sparta.dockingfinalproject.education.EducationRepository;
import com.sparta.dockingfinalproject.exception.DockingException;
import com.sparta.dockingfinalproject.exception.ErrorCode;
import com.sparta.dockingfinalproject.security.UserDetailsImpl;
import com.sparta.dockingfinalproject.security.jwt.JwtTokenProvider;
import com.sparta.dockingfinalproject.user.dto.KakaoUserInfoDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoUserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final EducationRepository educationRepository;

  public KakaoUserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
	  JwtTokenProvider jwtTokenProvider,
	  EducationRepository educationRepository) {

	this.userRepository = userRepository;
	this.passwordEncoder = passwordEncoder;
	this.educationRepository = educationRepository;
	this.jwtTokenProvider = jwtTokenProvider;

  }

  public Map<String, Object> kakaoLogin(String code) throws JsonProcessingException {

	if (code == null) {
	  throw new DockingException(ErrorCode.CODE_NOT_FOUND);
	}

	// 1. "?????? ??????"??? "????????? ??????" ??????
	String accessToken = getAccessToken(code);

	// 2. ???????????? ????????? API ??????
	KakaoUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);

	// 3. DB ??? ????????? Kakao Id ??? ????????? ??????
	Long kakaoId = kakaoUserInfo.getId();
	User kakaoUser = userRepository.findByKakaoId(kakaoId).orElse(null);

	// 4. ????????? ????????? ????????? user ?????????
	Map<String, Object> data = new HashMap<>();
	if (kakaoUser == null) {
	  kakaoUser = registerUserwithKakaoInfo(kakaoUserInfo, kakaoId);
	  data.put("userId", kakaoUser.getUserId());
	  data.put("nickname", kakaoUser.getNickname());
	  data.put("email", kakaoUser.getEmail());
	  data.put("userImgUrl", kakaoUser.getUserImgUrl());
	  data.put("phone", kakaoUser.getPhoneNumber());
	  data.put("token", jwtTokenProvider.createToken(kakaoUser.getEmail(), kakaoUser.getEmail()));

	  List<Map<String, Object>> eduList = new ArrayList<>();
	  Map<String, Object> edu = new HashMap<>();
	  Education education = new Education(kakaoUser);
	  educationRepository.save(education);
	  edu.put("????????????", education.getBasic());
	  edu.put("????????????", education.getAdvanced());
	  edu.put("????????????2", education.getCore());
	  eduList.add(edu);
	  data.put("eduList", eduList);
	}


	// 5. ?????? ????????? ??????
	forceLogin(kakaoUser);


	return SuccessResult.success(data);
  }

  //////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////????????? ?????????//////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////

  private String getAccessToken(String code) throws JsonProcessingException {

	// HTTP Header ??????
	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

	// HTTP Body ??????
	MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
	body.add("grant_type", "authorization_code");
	body.add("client_id", "b288c56fd31bb6f686ba8a3a39ba7fb2");
	body.add("redirect_uri", "http://localhost:3000/oauth/callback/kakao");
	System.out.println("?????? ?????? ??? " + code);
	body.add("code", code);

	//http header??? body??? ????????? ??????????????? ??????
	HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);

	//Http ???????????? - post??????
	RestTemplate rt = new RestTemplate();
	ResponseEntity<String> response = rt.exchange(
		"https://kauth.kakao.com/oauth/token",
		HttpMethod.POST,
		kakaoTokenRequest,
		String.class
	);

	// HTTP ?????? (JSON) -> ????????? ?????? ??????
	String responseBody = response.getBody();
	ObjectMapper objectMapper = new ObjectMapper();
	JsonNode jsonNode = objectMapper.readTree(responseBody);

	return jsonNode.get("access_token").asText();

  }

  private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {

	HttpHeaders headers = new HttpHeaders();
	// Http Header ??????
	headers.add("Authorization", "Bearer " + accessToken);
	headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

	HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);

	//Http ???????????? - Post??????
	RestTemplate rt = new RestTemplate();
	ResponseEntity<String> response = rt.exchange(
		"https://kapi.kakao.com/v2/user/me",
		HttpMethod.POST,
		kakaoUserInfoRequest,
		String.class
	);

	String responseBody = response.getBody();
	responseBody = response.getBody();

	ObjectMapper objectMapper = new ObjectMapper();

	JsonNode jsonNode = objectMapper.readTree(responseBody);
	Long id = jsonNode.get("id").asLong();
	String nickname = jsonNode.get("properties")
		.get("nickname").asText();
	String email = jsonNode.get("kakao_account")
		.get("email").asText();
	String userImgUrl = "https://gorokke.shop/image/profileDefaultImg.jpg";

	//????????? ???????????????
	try {
	  userImgUrl = jsonNode.get("properties")
		  .get("profile_image").asText();
	} catch (Exception ignored) {
	}

	System.out.println("????????? ????????? ??????: " + id + ", " + nickname + ", " + email + "," + userImgUrl);

	String username = email;

	return new KakaoUserInfoDto(id, nickname, email, username, userImgUrl);

  }

  private User registerUserwithKakaoInfo(KakaoUserInfoDto kakaoUserInfo, Long kakaoId) {
	User kakaoUser;
	// ????????????
	// username: kakao nickname
	String nickname = kakaoUserInfo.getNickname();

	// password: random UUID
	String password = UUID.randomUUID().toString();
	String encodedPassword = passwordEncoder.encode(password);

	// email: kakao email
	String email = kakaoUserInfo.getEmail();

	// role: ?????? ?????????
	//UserRoleEnum role = UserRoleEnum.USER;
	String username = email;

	//kakaoUserinfo?????? ??????????????? ????????? ????????? ????????????
	String userImgUrl = kakaoUserInfo.getUserImgUrl();

	kakaoUser = new User(username, password, nickname, email, kakaoId, userImgUrl);

	userRepository.save(kakaoUser);

	return kakaoUser;
  }

  private void forceLogin(User kakaoUser) {
	UserDetails userDetails = new UserDetailsImpl(kakaoUser);
	Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
		userDetails.getAuthorities());
	SecurityContextHolder.getContext().setAuthentication(authentication);
	System.out.println("?????? ????????? ??????");
  }


}
