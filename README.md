#### Coding Conventions
[컨벤션 규칙] (https://javamin.tistory.com/entry/%ED%85%8C%EC%8A%A4%ED%8A%B8) <br>
[깃 브랜치 전략(gitflow)] (https://techblog.woowahan.com/2553/)



#### login url 
http://localhost:8080/oauth2/authorization/kakao

<br>
로그인 흐름
<br>
카카오 로그인 -> http://localhost:8080/api/login 으로 post 요청 
<br>
<br>
ex

<br>

```
const response = await fetch("http://localhost:8080/api/login", {
                method : "post",
                credentials : "include"
            });

```

<br>
값으로 accessToken과 refreshToken 받을 수 있음.
<br>
access token 만료 : 10분, refresh token 만료 : 2시간
<br>
아직 refresh 기능 없음 [2025년 6월 2일]
