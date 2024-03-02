package com.hingebridge.devops.configs;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    /*
    * Say, for e.g the user has an authority 'READ', Spring security would return it as ROLE_READ
    * This class converts the authorities from ROLE_READ to READ, which would be used as @PreAuthorize("hasAuthority('READ')")
    * instead of @PreAuthorize("hasAuthority('ROLE_TESTVIEW')")
    *
    * Another important thing is that, the class helped get rid of the "SCOPE_" prefix as well, so instead of SCOPE_READ, we now have just READ
    * */

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        var extraDetails = (Map<String, List<String>>) jwt.getClaim("extraDetails");

        return extraDetails.get("authorities").stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    @Override
    public <U> Converter<Jwt, U> andThen(Converter<? super Collection<GrantedAuthority>, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}