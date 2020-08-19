package com.mitrais.chipper.tk.be.profileservice.config.audit;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String currentEmail = "mail@mail.com";
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            currentEmail = ((UserPrincipal) authentication.getPrincipal()).getName();
//        }

        return Optional.of(currentEmail);
    }

}
