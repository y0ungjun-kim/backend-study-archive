회원 가입- 사용자가 보낸 회원 정보를 받아서 검사한 뒤 안전하게 저장하고 결과를 알려주는 기능 

서버 입장에서 이 데이터가 정상인지 확인하고, 저장가능한지 판단 비밀번호는 바꾸고 
회원 정보 저장 결과를 돌려주는 흐름 
서버 안에서 흐름 
1. 요청 받기
2. 요청 데이터 꺼내기
3. 값 검증하기
4. 중복 회원인지 확인하기
5. 비밀번호 암호화하기
6. 저장용 객체 만들기
7. 저장하기
8. 응답 만들기
9. 응답 보내기

Controller
Service
Repository
Entity
DTO

controller: 사용자가 보낸 http 요청을 처음 받는 곳 
service 회원 가입 로직의 핵심이 들어가는 곳 
Repository: 저장 담당 
repository가 저장소에 데이터를 넣는다
entity:
저장될 데이터의 형태 
dto:
dto는 두 가지로 나뉜다.
요청 dto-> 사용자가 보낸 데이터를 담는 객체
SignupRequest
응답 dto-> 서버가 돌려줄 결과를 담는 객체
SignupResponse
