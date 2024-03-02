package com.hingebridge.devops.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.hingebridge.devops.constants.Endpoints.INDEX;
import static com.hingebridge.devops.constants.Endpoints.INDEX_BASE_URL;

@Slf4j
@RestController
@RequestMapping(INDEX_BASE_URL)
public class IndexController {

    @GetMapping(INDEX)
    @PreAuthorize("hasAuthority('TESTVIEW')")
    public Jwt test(@AuthenticationPrincipal Jwt jwt) {
        return jwt;
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('TESTVIEW')")
    public String test1(@AuthenticationPrincipal Jwt principal) {
        Map<String, Object> claims = principal.getClaims();

        return "Hello world";
    }

    @GetMapping("/home")
    public String test2(@AuthenticationPrincipal Jwt principal) {
        Map<String, Object> claims = principal.getClaims();

        return "Hello world: Welcome to home page";
    }
}