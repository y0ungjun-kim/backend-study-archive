# MemberController

## 역할
`MemberController`는 회원 관련 HTTP 요청을 받는 컨트롤러이다.  
현재는 회원가입 요청을 처리하는 역할을 담당한다.

```java
package com.example.demo.controller;

import com.example.demo.dto.SignupRequest;
import com.example.demo.dto.SignupResponse;
import com.example.demo.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public SignupResponse signup(@RequestBody SignupRequest request) {
        return memberService.signup(request);
    }
}


```
## 클래스 설명
- `@RestController`
  - 이 클래스가 REST API 요청을 처리하는 컨트롤러임을 의미한다.
  - 반환값은 주로 JSON 형태로 응답
  

- `@RequestMapping("/members")`로 설정
    - 이 컨트롤러의 기본 URL 경로를 `/members`로 설정한다.
    - 따라서 이 클래스 안의 API는 `/members`를 공통 경로로 가진다.
## 의존관계
- `MemberController`는 `MemberService`에 의존한다.
- 컨트롤러는 직접 비즈니스 로직을 처리하지 않고, 서비스 계층에 작업을 위임한다.

```java
private final MemberService memberService;
public MemberController(MemberService memberService) {
    this.memberService = memberService;
}
```

## 동작 과정

1. 클라이언트가 /members/signup으로 POST 요청을 보낸다.
2. 요청 본문(body)의 JSON 데이터가 SignupRequest 객체로 변환된다.
3. 컨트롤러는 memberService.signup(request)를 호출한다.
4. 서비스 계층에서 회원가입 로직을 수행한다.
5. 결과를 SignupResponse 형태로 반환한다.

