package com.mitrais.chipper.tk.be.profileservice.config;


import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JwtConfig {
    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret}")
    private String secret;
}
