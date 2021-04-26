package com.imadelfetouh.zuulproxyservice;

import com.imadelfetouh.zuulproxyservice.filter.JWTFilter;
import com.imadelfetouh.zuulproxyservice.filter.SignInSignUpFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableZuulProxy
@SpringBootApplication
public class ZuulproxyserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulproxyserviceApplication.class, args);
    }

    @Bean
    public SignInSignUpFilter signInFilter() {
        return new SignInSignUpFilter();
    }

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter();
    }

    @Bean
    public CorsFilter corsFilter() {
        final org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://52.154.216.20:3000");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

}
