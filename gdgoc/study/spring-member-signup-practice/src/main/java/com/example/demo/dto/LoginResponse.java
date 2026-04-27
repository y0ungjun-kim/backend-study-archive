package com.example.demo.dto;

public class LoginResponse {
    private String accessToken;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
} //로그인 성공 후 서버가 클라이언트에게 accessToken을 반환하기 위한 DTO
