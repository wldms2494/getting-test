# getting-final-project


:house_with_garden:Intro 
========================

<img src = "https://github.com/hanhae99-final-project-13/Getting-FrontEnd/blob/main/public/img/GUIicon/getting_readme.svg" width = "500">


저희 개팅은

반려견에 대한 교육 지식과 퀴즈를 통해 예비 견주님들께는 반려견을 키울 수 있다는 자신감을,

임보자님들께는 믿고 맡길 수 있는 예비 견주님들을 만나실 수 있는 기회를 제공하고자 합니다.

이를 바탕으로 올바른 반려견 입양 문화를 지향하는 유기견 입양 웹앱 서비스입니다.

<p style='font-size:22px;'>🌐<a href='https://getting.co.kr' target='_blank'>개팅 바로가기!</a></p>
<p style='font-size:22px;'>🌐<a href='https://www.notion.so/Getting-f2a8f1ac6dea41f79d60b318f7c0e41a' target='_blank'>Notion</a></p>


:ledger:메인기능
=========================
- 유기견 반려견 교육자료와 퀴즈 제공
- 개인으로서 유기견 분양 게시글을 올릴 수 있으며, 보호소에 등록된 유기견들도 조회
- 사용자가 직접 입양 신청서를 업로드 가능
- 문자 서비스
- JWT로그인, 소셜로그인
- 알림 서비스를 통해 입양 후 케어 서비스 제공

🚀DEMO
==========================


|  회원가입  | 로그인 |  문자인증  |
|-----------|---------------|--------------|
|<img src = "https://user-images.githubusercontent.com/80088918/144443642-907e7e9b-a8ef-470a-8b3b-0ff967c1fae8.gif" width="200" >|<img src = "https://user-images.githubusercontent.com/80088918/144443734-2543a393-60c8-43b2-bdbe-9eac1183ca00.gif" width="200" >|<img src = "https://user-images.githubusercontent.com/80088918/144418489-3de7f101-e617-4ff1-9d1e-74530ce17b69.gif" width="200" >|




|     필수지식|심화지식1|  심화지식2    |
|-----------|---------------|--------------|
|<img src = "https://user-images.githubusercontent.com/80088918/144421566-54b89915-ac3c-41a6-a401-6e4418ed5dd5.gif" width="200" >|<img src = "https://user-images.githubusercontent.com/80088918/144418712-90841861-9e11-41b2-b590-a5a9d09c86d6.gif" width="200" >|<img src = "https://user-images.githubusercontent.com/80088918/144422293-ff538bcd-42f0-444b-bcb0-c5fc84eaa332.gif" width="200" >|



|     필수퀴즈|심화퀴즈1|  심화퀴즈2    |
|-----------|---------------|--------------|
|<img src = "https://user-images.githubusercontent.com/80088918/144418674-07ac232e-5756-4ad1-be79-210dd4021998.gif" width="200" >|<img src = "https://user-images.githubusercontent.com/80088918/144418777-09fd3839-b4f8-42f2-917e-6514e9280838.gif" width="200" >|<img src = "https://user-images.githubusercontent.com/80088918/144418877-87275ab8-7043-4d3c-abad-63649f596682.gif" width="200" >|



|     관심 등록|나의 입양 신청|  입양 승인    |입양 거절|
|-----------|---------------|--------------|-------------------|
|<img src = "https://user-images.githubusercontent.com/80088918/144427722-e8bf4e3a-9fe8-4f6c-a0f0-7e408f94bf15.gif" width="200" >|<img src = "https://user-images.githubusercontent.com/80088918/144429889-03b88709-db76-4245-a165-aab9be49547c.gif" width="200" >|<img src = "https://user-images.githubusercontent.com/80088918/144427804-e4acf810-6ae5-4ab7-aeb4-72196fc53301.gif" width="200" >|<img src = "https://user-images.githubusercontent.com/80088918/144427832-6295e9b5-4bdc-410a-bbb8-1f12a4886a69.gif" width="200" >|



|                                                                                                            분양 게시글 작성 :pencil2:|입양 신청서 작성:pencil2:|
|--------------------------------------------------------------------------------------------------------------------------------|---|
|<img src = "https://user-images.githubusercontent.com/80088918/144418898-f5bd5b5f-f45d-4517-b01f-7677436cfa8c.gif" width="200" >|<img src = "https://user-images.githubusercontent.com/80088918/144427658-ce665791-b6da-4825-90b1-52885c8cb601.gif" width="200" >|




|                                                                                                      댓글 | 알람  |
|--------------------------------------------------------------------------------------------------------------------------------|---|
|<img src = "https://user-images.githubusercontent.com/80088918/144443534-6daf3fcd-d4a0-4c63-897e-1e269a84ae98.gif" width="200" >|<img src = "https://user-images.githubusercontent.com/80088918/144435979-195f40da-2335-48e6-b8a7-4a96259197a3.gif" width="200" >|



Tech Structure
================

<img src = "https://github.com/hanhae99-final-project-13/Getting-FrontEnd/blob/main/public/img/GUIicon/achitecture.PNG" width = "800">


주요 기능
==================



## ⚙ 주요 기능


<details>
<summary>로그인</summary>

+ JWT 토큰 방식을 이용하여 로그인이 가능합니다.
+ 카카오 소셜 로그인이 가능합니다.
</details>

<details>
<summary>회원가입</summary>

+ 아이디 및 닉네임의 중복확인을 자동으로 체크합니다.
+ 문자 인증을 통해 휴대폰 인증을 할 수 있습니다.
</details>

<details>
<summary>메인 페이지</summary>

+ 최신 등록된 유기견들을 조회할 수 있습니다. (최대 6건)
+ 서비스 소개 페이지를 조회할 수 있습니다.
+ 필수지식 페이지를 조회할 수 있습니다.
</details>

<details>
<summary>입양하기 페이지</summary>

+ 유기견들의 간략한 정보를 볼 수 있고 상세 정보를 조회할 수 있습니다.
+ 분양 글 등록 기능이 있습니다.
+ 관심 등록한 강아지를 최상단에서 조회할 수 있습니다.
+ 조건(기간, 장소, 지역)과 일치한 유기견 검색이 가능합니다. 
+ 최신순, 등록순으로 유기견을 조회할 수 있습니다.
+ 무한 스크롤 기능을 통해 분양글을 더 불러올 수 있습니다.
</details>

<details>
<summary>상세 페이지</summary>

+ 유기견들의 자세한 정보를 조회할 수 있습니다.
+ 유기견의 관심 등록(북마크)이 가능합니다.
+ 개팅의 필수지식을 이수한 경우 유기견의 입양을 신청할 수 있습니다.
+ 글 작성자의 경우 게시글 수정, 삭제가 가능합니다.
+ 댓글을 통해 여러 유저와 소통할 수 있습니다.
+ 댓글 등록자의 경우 댓글의 수정, 삭제가 가능합니다.
</details>

<details>
<summary>입양신청 페이지</summary>

+ 입양 신청자의 상세한 정보와 입양 될 반려견이 거주할 곳의 이미지 등을 분양글 등록자에게 전달합니다.
</details>

<details>
<summary>입양지식 페이지</summary>

+ 필수지식, 심화지식 1, 2를 조회할 수 있습니다.
+ 조회 후 내용을 기반으로 한 퀴즈를 풀어 교육을 이수하고 뱃지를 얻을 수 있습니다.
+ 필수지식을 이수해야만 입양 신청이 가능하며, 입양 신청시 글 등록자가 교육 이수 뱃지를 확인할 수 있습니다.
</details>

<details>
<summary>마이페이지</summary>

+ 프로필 사진 등록, 수정이 가능합니다.
+ 관심 친구로 등록한 유기견의 조회가 가능합니다.
+ 입양 신청한 게시글의 조회 및 입양 승락, 반려 조회가 가능합니다.
+ 내가 등록한 글을 조회할 수 있고, 입양을 신청한 사람의 교육 이수 현황과 정보, 입양 신청서 조회가 가능합니다.
</details>

<details>
<summary>알람페이지</summary>

+ 유저가 댓글 작성 시 실시간 알림 확인이 가능합니다.
+ 댓글 알림의 경우 클릭 시 해당 게시글로 이동합니다.
+ 유저가 입양을 신청했을 경우 실시간 알림 확인이 가능합니다.
+ 입양 신청의 경우 클릭 시 해당 유저의 신청서를 조회할 수 있습니다.
</details>

---





