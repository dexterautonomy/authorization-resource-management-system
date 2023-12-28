package com.hingebridge.devops.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hingebridge.devops.constants.Endpoints.INDEX;
import static com.hingebridge.devops.constants.Endpoints.INDEX_BASE_URL;

@Slf4j
@RestController
@RequestMapping(INDEX_BASE_URL)
public class IndexController {
    @GetMapping(INDEX)
    @PreAuthorize("hasAuthority('ROLE_VIEW')")
    public Jwt test(@AuthenticationPrincipal Jwt jwt) {
        return jwt;
    }

    @GetMapping("/test")
    public String test2() {
        return "Not authenticated";
    }
}