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
 그 이유는 signupRequest는 요청용 객체
 Member는 db 저장용 객체 

 signupRequest는
 사용자가 보낸 원본 데이터, password는 평문 상태
 Member 
 db에 저장할 객체 
 password는 해싱된 값이 들어가야한다.

 SignupRequest
→ 검증
→ 비밀번호 해싱
→ Member 생성
→ DB 저장


실제 흐름 예시 

{
  "email": "test@test.com",
  "password": "1234",
  "name": "kim"
}

signupRequest에 들어가고 
 서비스 에서 
String encodedPassword = passwordEncoder.encode(request.getPassword());

Member member = new Member();
member.setEmail(request.getEmail());
member.setPassword(encodedPassword);
member.setName(request.getName());
이렇게 Member로 바뀐다

그 다음 signupResponse 
회원 가입이 성공하면 서버는 json형식으로 응답을 보낸다.
이 json을 만들기 위한 자바 객체가 signupResponse

signupresponse를 따로 만드는 이유 
Member에는 password 필드가 있다.그런데 회원가입 응답에 비밀번호를 보내면 안된다.
엔티티를 그대로 반환하면 위험,엔티티는 DB구조고 응답 dto 는 api 구조 둘을 분리해두면 나중에 수정하기 편하다

--
memberRepository 

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);
}
JpaRepository를 상속하면 스프링 데이터 JPA가 기본적인 DB 기능을 자동으로 제공해준다.
예를 들면:

저장 save()
id로 조회 findById()
전체 조회 findAll()
삭제 delete()

이런 걸 직접 구현 안 해도 돼.
"Member 엔티티를 위한 JPA Repository를 만들겠다"

우리가 Member에서 이렇게 했지:
private Long id;
그래서 기본키 타입은 Long이야.

JpaRepository를 상속하면 자동으로 생기는 기능

이 인터페이스만 만들어도 이미 이런 걸 쓸 수 있어:

memberRepository.save(member);
memberRepository.findById(1L);
memberRepository.findAll();
memberRepository.delete(member);

즉, SQL을 직접 안 써도 기본 CRUD가 가능한 거야.


6. findByEmail 메서드
Optional<Member> findByEmail(String email);

이것도 중요해.
이 메서드는 우리가 직접 구현하지 않았는데도
스프링 데이터 JPA가 메서드 이름을 보고 자동으로 쿼리를 만들어줘.

