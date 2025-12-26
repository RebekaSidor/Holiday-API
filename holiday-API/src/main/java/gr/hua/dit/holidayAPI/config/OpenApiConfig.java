package gr.hua.dit.holidayAPI.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("holidayAPI")
                        .version("v1")
                        .description("Stateless REST API for national holidays"));
    }

    @Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi.builder()
                .group("api")
                .packagesToScan("gr.hua.dit.holidayAPI.web.rest")
                .pathsToMatch("/api/**")
                .build();
    }
}
