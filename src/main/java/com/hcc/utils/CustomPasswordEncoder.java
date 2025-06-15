package com.hcc.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    private final BCryptPasswordEncoder delegate;

    // Constructor initializes the BCryptPasswordEncoder
    public CustomPasswordEncoder() {
        this.delegate = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return delegate.encode(rawPassword); // Delegates the encoding to BCryptPasswordEncoder
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return delegate.matches(rawPassword, encodedPassword); // Delegates the matching
    }
}