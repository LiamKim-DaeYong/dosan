package com.samin.dosan.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.modelmapper.config.Configuration.*;

@Configuration
@EnableJpaAuditing
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);

        return modelMapper;
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String loginUser = authentication.getName();
                return Optional.ofNullable(loginUser);
            }
        };
    }
}
