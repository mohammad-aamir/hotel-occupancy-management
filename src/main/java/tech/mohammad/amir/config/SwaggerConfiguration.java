package tech.mohammad.amir.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;

import static java.util.Collections.emptyList;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static springfox.documentation.builders.RequestHandlerSelectors.*;
import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;
import static tech.mohammad.amir.common.constant.Constants.*;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket docket() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(basePackage(API_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo());
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfo(API_TITLE, API_TITLE, API_VERSION, EMPTY, DEFAULT_CONTACT, EMPTY, EMPTY, emptyList());
    }
}
