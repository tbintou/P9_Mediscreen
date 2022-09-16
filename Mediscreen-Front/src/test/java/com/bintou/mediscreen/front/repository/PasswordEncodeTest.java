package com.bintou.mediscreen.front.repository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncodeTest {

    @Test
    public void testPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pw = encoder.encode("Aaaaaaaa+Bbbbbbbbb8");
        System.out.println("[ "+ pw + " ]");
    }
}

