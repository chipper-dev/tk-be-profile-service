package com.mitrais.chipper.tk.be.profileservice.config.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditConfiguration {

	@Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}
