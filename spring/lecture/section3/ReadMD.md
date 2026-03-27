<!--인터페이스 만들기-> 지금 당장 구현의 하되 나중에 구현 방법이 바뀌어도 갈아끼기 쉽게 하기 위해서 
회원 저장소가 아직 만들어지지 않았는데 메모리로 할 지 RDB로 할지 
그래서 만들어 놓고 새 구현체로 바꾸어 끼우기 위해 -->

public class MemberServiceimpl implements MemberService {

    private final MemberRepository memberRepository =new MemoryMemberRepository(); //이 부분
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberID) {
        return memberRepository.findById(memberID);
    }
}

<!-- 구현체가 아니라 인터페이스 타입으로 선언 MemberServiceImpl은 MemberRepository라는 역할에 의존하고, 실제 동작은 MemoryMemberRepository 객체를 만들어 사용한다.
여기서 join()은 직접 저장하지 않아. 그냥 memberRepository에게 “저장해줘”라고 시키는 거야. 근데 실제 들어있는 객체는 MemoryMemberRepository니까, 실제로는 그 구현체의 save()가 실행돼.
-->

private final MemberRepository memberRepository;
public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
}
