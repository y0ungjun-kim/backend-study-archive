package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.*;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public SignupResponse signup(SignupRequest request) {
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new IllegalArgumentException("이메일은 필수입니다");
        }
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("이름은 필수입니다.");
        }

        // 2. 이메일 중복 확인
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member member =new Member();
        member.setEmail(request.getEmail());
        member.setPassword(encodedPassword);
        member.setName(request.getName());
        Member savedMember =memberRepository.save(member);
        return new SignupResponse(
                savedMember.getId(),
                savedMember.getEmail(),
                savedMember.getName()
        );
    }

    public LoginResponse login(LoginRequest request){
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("가입되지 않은 이메일 입니다."));
        if(!passwordEncoder.matches(request.getPassword(),member.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        String token = jwtTokenProvider.createToken(member.getId(),member.getEmail());
        return new LoginResponse(token);
    }

 public MemberResponse findMember(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        return new MemberResponse(
                member.getId(),
                member.getEmail(),
                member.getName()
        );

 }

    public List<MemberResponse> findMembers(List <Long> ids){
        return ids.stream()
                .map(id -> memberRepository.findById(id)
                        .orElseThrow(()->new IllegalArgumentException("회원을 찾을 수 없습니다. id="+ id)))
                .map(member -> new MemberResponse(
                        member.getId(),
                        member.getEmail(),
                        member.getName()
                ))
                .toList();


    }
 public MemberResponse updateMember(Long memberId,MemberUpdateRequest request){
     Member member = memberRepository.findById(memberId)
             .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. id=" + memberId));

     if (request.getName() == null || request.getName().isBlank()) {
         throw new IllegalArgumentException("이름은 필수입니다.");
     }

     member.setName(request.getName());

     Member savedMember = memberRepository.save(member);

     return new MemberResponse(
             savedMember.getId(),
             savedMember.getEmail(),
             savedMember.getName()
     );
 }





}
