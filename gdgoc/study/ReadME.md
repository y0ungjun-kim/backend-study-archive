# Spring Member Signup Practice

스프링 부트로 회원가입 기능의 기본 흐름을 실습한 프로젝트입니다.

## 학습 목표
- Controller / Service / Repository 역할 분리 이해
- DTO와 Entity의 차이 이해
- 비밀번호 해싱 적용
- Spring Security 기본 설정 학습
- 회원가입 API 요청/응답 흐름 이해

## 구현 기능
- 회원가입 API (`POST /members/signup`)
- 이메일 중복 검사
- 비밀번호 해싱
- 메모리 기반 회원 저장소 구현

## 프로젝트 구조
src/main/java/com/example/demo
- controller: API 요청 처리
- service: 회원가입 비즈니스 로직
- repository: 회원 저장소
- domain: Member 엔티티
- dto: 요청/응답 DTO
- config: 보안 및 PasswordEncoder 설정

## 회원가입 처리 흐름
1. 클라이언트가 회원가입 요청 전송
2. Controller가 요청 수신
3. Service가 요청값 검증
4. 중복 이메일 확인
5. 비밀번호 해싱
6. Member 객체 생성
7. Repository 저장
8. 응답 반환

## API 예시

### 요청
POST /members/signup

```json
{
  "email": "test@test.com",
  "password": "1234",
  "name": "kim"
}

