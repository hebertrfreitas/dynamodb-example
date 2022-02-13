package com.hebertrfreitas.dynamodbexample.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun configureOpenAPI(): OpenAPI =
        OpenAPI().info(Info().title("Painters API - a simple api to demonstrate dynamodb"))

}