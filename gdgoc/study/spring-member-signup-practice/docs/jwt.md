## Jwt 실습


LoginRequest 코드 안에 빈 생성자 

``` java
package spring.member.dto;
public class LoginResponse {

    private String accessToken;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getAccessToken() {
        return accessToken;
    }
}
```

여기서 빈 생성자가 있어야 하는 이유

빈 생성자는 스프링이 JSON을 객체로 바꿀 때 필요하다.

클라이언트가 이렇게 보내면
```Json
{
  "email": "test@test.com",
  "password": "1234"
}
```
스프링은 내부적으로 대략 이런 식으로 처리해.
```java
LoginRequest loginRequest = new LoginRequest();
loginRequest.email = "test@test.com";
loginRequest.password = "1234";
```
빈 객체를 만든 다음 json 값을 필드에 넣는다.
스프링/Jackson이 JSON 데이터를 DTO 객체로 변환하기 위해 사용하는 생성자


