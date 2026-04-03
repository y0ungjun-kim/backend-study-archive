핵심 개념 먼저
PasswordEncoder란?
비밀번호를 암호화(해싱) 해주는 객체

예:

1234 → $2a$10$...

이렇게 바꿔주는 역할이야.

어디에 만들까?

보통 이런 설정은 config 패키지에 둬.

src/main/java/com/example/demo/config/SecurityConfig.java

👉 config 패키지 없으면 새로 만들어도 돼.


package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
