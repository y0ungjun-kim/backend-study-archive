package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return memberService.login(request);
    }

    @GetMapping("/{memberId}")
    public MemberResponse findMember(@PathVariable Long memberId) {
        return memberService.findMember(memberId);
    }
    @GetMapping
    public List<MemberResponse> findMembers(@RequestParam("ids") List<Long> ids){
        return memberService.findMembers(ids);
    }
    @PatchMapping("/{memberId}")
    public MemberResponse updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberUpdateRequest request)
    {
        return memberService.updateMember(memberId, request);
    }


}