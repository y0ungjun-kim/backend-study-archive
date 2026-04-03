# REST API

## REST API란?
REST API는 URL로 자원을 표현하고, HTTP 메서드로 동작을 구분하는 방식의 API 설계 방법이다.

## 핵심 개념
- URL은 자원(Resource)을 나타낸다.
- HTTP 메서드는 동작(Action)을 나타낸다.

## 예시
- GET /members : 회원 목록 조회
- GET /members/1 : 특정 회원 조회
- POST /members : 회원 생성
- DELETE /members/1 : 특정 회원 삭제

## 현재 프로젝트와 연결
현재 회원가입 API는 `POST /members/signup` 구조를 사용하고 있다.
이 방식은 회원가입 기능을 명확히 보여주지만, REST 관점에서는 `POST /members`처럼 자원 생성 중심으로 설계할 수도 있다.



## 기능 중심 URL과 자원 중심 URL

기능 중심 URL은 URL 안에 동작 이름을 포함하는 방식이다.
예를 들어 `/signup`, `/deleteMember`처럼 기능 자체를 경로에 표현한다.

자원 중심 URL은 URL에 대상을 나타내고, 동작은 HTTP 메서드로 구분하는 방식이다.
예를 들어 `POST /members`는 회원 자원을 생성하고,
`DELETE /members/1`은 특정 회원 자원을 삭제하는 의미를 가진다.

현재 프로젝트의 `POST /members/signup`은 기능 중심 URL에 가깝고,
REST 스타일로는 `POST /members`처럼 표현할 수도 있다.