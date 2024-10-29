package br.apae.ged.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigs {
    @Bean
    public OpenAPI openApiConfigurer(){
        Info information = new Info()
                .title("API Gerenciadora de Arquivos")
                .version("1.0")
                .description("Esta API expõe endpoints para uso de um gerenciador de arquivos");

        return new OpenAPI().info(information);
    }
}
