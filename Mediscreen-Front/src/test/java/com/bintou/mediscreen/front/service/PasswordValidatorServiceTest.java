package com.bintou.mediscreen.front.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class PasswordValidatorServiceTest {

    @ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @ValueSource(strings = {"Password$Test123456"})
    public void passwordRegex(String password) {
        Assertions.assertTrue(PasswordValidator.isValid(password));
    }

    @ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @ValueSource(strings = {"PasswordTest1"})
    public void passwordRegexInvalid(String password) {
        Assertions.assertFalse(PasswordValidator.isValid(password));
    }

    @Test
    public static Stream<String> passwordProvider() {
        return Stream.of(
                "HHHgggaaa°123",
                "passworD&456",
                "J<?@-z97",
                "0987654321+mnopQAR"
        );
    }

    @Test
    public static Stream<String> passwordProvideInvalid() {
        return Stream.of(
                "test1234",
                "toto",
                "&@!$-;<",
                "Password34",
                " ",
                ""
        );
    }

}

