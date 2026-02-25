package mx.com.ms.config;

/**
 * @project ms-employees
 * @created 25/02/2026
 * @author lvg
 */

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employees microservices")
                        .version("1.0.0")
                        .description("API for creating, updating, querying and delete employees"));
    }
}

