member entity 작성 
membersms 회원 한 명의 정보 담는 객체 ,
db테이블과 연결되는 클래스

2단계의 목표

우리가 만들고 싶은 회원가입 흐름에서 Member는 이런 역할을 해:

회원가입 요청이 들어옴
→ 요청 데이터를 꺼냄
→ Member 객체에 담음
→ DB에 저장

예를 들어 사용자가 이런 데이터를 보냈다고 하자:

{
  "email": "test@test.com",
  "password": "1234",
  "name": "kim"
}

그러면 서버에서는 나중에 이런 Member 객체를 만들게 돼:

Member member = new Member();
member.setEmail("test@test.com");
member.setPassword("암호화된값");
member.setName("kim");

이 객체가 DB에 저장되는 거야.

@Entity
db와 연결되는 엔티티 db에 저장되는 대상 

@Table(name = "members")
이 엔티티가 DB에서 members라는 테이블과 연결된다는 뜻이야.

@Id

뜻

이 필드가 기본키(PK) 라는 뜻이야.

DB에서 기본키는:

각 행을 구분하는 고유값
중복되면 안 됨
회원 한 명을 식별하는 기준

즉 id는 회원의 대표 번호야.
6-3. @GeneratedValue(strategy = GenerationType.IDENTITY)
@GeneratedValue(strategy = GenerationType.IDENTITY)
뜻

id 값을 우리가 직접 넣지 않고, DB가 자동으로 증가시켜서 넣는다는 뜻이야.

예를 들어 첫 번째 회원 저장:

id = 1

두 번째 회원 저장:

id = 2

세 번째 회원 저장:

id = 3

이런 식이야.

그래서 회원가입할 때는 보통 id를 안 넣어

다음 단계 signupRequest 작성

여기서 드는 의문 

왜 Member랑 따로 만드냐

이거 중요해.

처음 보면 이런 생각이 들 수 있어:

"어차피 email, password, name 다 똑같은데
왜 Member 하나만 쓰면 안 돼?"

그런데 보통은 분리하는 게 맞아.





