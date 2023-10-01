package com.souzatech.apiprodutos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    @Bean
    public String instantiateDatabase(){
        return "Profile de Produção iniciado - PostgreSQL";
    }
}
