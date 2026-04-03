# DI (Dependency Injection)

## DI란?
DI는 Dependency Injection의 줄임말로, 의존성 주입을 의미한다.
어떤 클래스가 필요한 객체를 직접 생성하지 않고 외부에서 전달받는 방식이다.

## 의존성이란?
한 클래스가 다른 클래스의 기능을 필요로 하는 관계를 말한다.
예를 들어 MemberController는 MemberService를 필요로 하므로 MemberService에 의존한다.

## 주입이란?
필요한 객체를 직접 생성하는 대신 생성자 등을 통해 외부에서 전달받는 것을 말한다.

## 예시
```java
private final MemberService memberService;

public MemberController(MemberService memberService) {
    this.memberService = memberService;
}
```
