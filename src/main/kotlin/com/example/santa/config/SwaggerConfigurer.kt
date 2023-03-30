package com.example.santa.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition
@Configuration
class SwaggerConfigurer {
    @Bean
    fun configure(): OpenAPI {
        return OpenAPI().info(Info().title("Secret Santa API").description("Documentation for Secret Santa API").version("1.0.0"))
    }
}