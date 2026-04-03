# 회원가입 흐름

## 1. 전체 흐름
클라이언트가 회원가입 요청을 보내면 Controller가 요청을 받고, Service가 비즈니스 로직을 처리한 뒤 Repository를 통해 회원 정보를 저장한다.

## 2. 요청 처리 순서
1. 클라이언트가 회원가입 요청을 보낸다.
2. `MemberController`가 요청 데이터를 받는다.
3. 요청 데이터는 `SignupRequest` DTO로 전달된다.
4. `MemberService`가 회원 생성 로직을 수행한다.
5. 비밀번호는 암호화 후 저장된다.
6. `MemberRepository`가 `Member` 엔티티를 저장한다.
7. 저장 결과를 바탕으로 `SignupResponse`를 반환한다.

## 3. 계층별 역할
### Controller
- HTTP 요청과 응답을 처리한다.
- DTO를 통해 요청 데이터를 전달받는다.

### Service
- 회원가입과 관련된 비즈니스 로직을 수행한다.
- 비밀번호 암호화, 엔티티 생성 등을 담당한다.

### Repository
- 회원 정보를 DB에 저장하고 조회한다.

# SignupRequest

## 역할
`SignupRequest`는 회원가입 요청 데이터를 담는 DTO이다.  
클라이언트가 회원가입할 때 보낸 JSON 데이터를 서버에서 받기 위해 사용한다.

즉, 사용자가 입력한 회원가입 정보(email, password, name)를
Controller가 자바 객체 형태로 받기 위한 클래스이다.

---

## 왜 필요한가?
회원가입 요청이 들어오면 클라이언트는 보통 JSON 형태로 데이터를 보낸다.

예:
```json
{
  "email": "test@example.com",
  "password": "1234",
  "name": "kim"
}
```








##  이번 실습에서 배운 점
- Controller, Service, Repository를 분리하면 역할이 명확해진다.
- DTO를 사용하면 API 요청/응답과 엔티티를 분리할 수 있다.
- 비밀번호는 평문이 아니라 암호화해서 저장해야 한다.


복습용 질문
* 왜 SignupRequest가 필요하지?
    * 즉, SignupRequest는 회원가입 요청 데이터를 담는 전용 객체야.
  

* 왜 Member 엔티티로 바로 안 받지? 이유는 역할 분리 때문이야.

```SignupRequest```
요청을 받기 위한 객체 API 중심<br>

```Member```
DB에 저장할 객체
엔티티 중심  엔티티로 바로 받으면 문제가 생길 수 있어.<br>

* setter는 누가 사용할까?
* 주로 스프링이 사용해.
    클라이언트가 보낸 JSON 데이터를 SignupRequest 객체로 바꿀 때,
    스프링이 내부적으로 setter를 호출해서 값을 넣어줄 수 있어.
    
    예를 들어:
    
    JSON의 ```"email"``` → ```setEmail(...)```
* 
    JSON의 ```"password"``` → ```setPassword(...)```
* 
    JSON의 ```"name"``` → ```setName(...)```
    
    이렇게 연결돼.
즉, 우리가 직접 setter를 부를 수도 있지만,
이 경우엔 보통 요청 데이터를 객체로 바인딩하는 과정에서 스프링이 사용한다고 보면 돼.


* getter는 어디서 사용할까?
  * 보통 Service나 Controller에서 사용해.
    
  * 예를 들어 컨트롤러가 SignupRequest request를 받으면,
  * 서비스로 넘긴 뒤 서비스에서 이렇게 쓸 수 있어:
  * 
* 이 객체는 DB에 저장되는 객체일까, 전달용 객체일까?
  * 전달용 객체
  * 즉, SignupRequest 자체를 DB에 저장하는 게 아니라
  이 안의 값을 꺼내서 Member 엔티티를 만들고,
  그 Member를 DB에 저장하는 거야. 


