package com.gabriel.minhacasa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
     
     @Bean
     public OpenAPI configOpenAPI() {
          return new OpenAPI().info(
               new Info().description("MinhaCasa backend - documentação")
               .title("MinhaCasa")
               .version("1.0.0")
          );
     }
}
