## DTO <br>
**DTO**는 Data Transfer Object의 줄임말
데이터를 전달하기 위한 객체 
클라이언트가 보낸 요청 데이터를 받거나, 서버가 응답 데이터를 돌려줄 때
그 데이터를 담는 전용 바구니

###  왜 쓰는가??

예를 들어 회원가입에서 ```Member``` 엔티티가 있다고 해보자.

Member에는 이런 값이 있을 수 있어:

+ id
+ username
+ password 
+ role
+ createdAt

그런데 회원가입 요청에서는 모든 값이 다 필요하지 않을 수 있어.<br>
사용자가 보내는 건 보통:<br>
+ username
+ password
+ name<br>

그래서 요청용 DTO를 따로 만듦.

**예시**
```java
    public class SignupRequest {
        private String username;
        private String password;
        private String name;
    }

```
응답의 경우
회원가입 후 굳이 password까지 돌려줄 필요는 없음
```java
public class SignupResponse {
    private Long id;
    private String username;
    private String name;
    }
```