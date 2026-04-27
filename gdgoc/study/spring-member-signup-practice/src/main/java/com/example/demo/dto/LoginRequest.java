package com.example.demo.dto;

public class LoginRequest {

    private String email;
    private String password;

    public LoginRequest(){
    } //기본 생성자가 있어야하는 이유
    // 스프링이 json을 객체로 바꿀때 필요하다 .
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public LoginRequest(String password, String email) {
        this.password = password;
        this.email = email;
    }





}
