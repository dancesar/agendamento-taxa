package com.financial.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                .title("API Financial Scheduler")
                .version("1.0")
                .description("Documentação da API de agendamento de transferências financeiras"));
    }

    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("transferencias")
                .pathsToMatch("/transferencias/**")
                .build();
    }
}
