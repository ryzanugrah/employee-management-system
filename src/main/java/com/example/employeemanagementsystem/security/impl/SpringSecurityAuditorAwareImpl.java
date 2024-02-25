package com.example.employeemanagementsystem.security.impl;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityAuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable("AUTHOR").filter(s -> !s.isEmpty());
    }
}
