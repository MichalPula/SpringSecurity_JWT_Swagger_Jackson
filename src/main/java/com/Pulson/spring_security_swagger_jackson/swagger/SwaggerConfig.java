package com.Pulson.spring_security_swagger_jackson.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.Pulson.spring_security_swagger"))
                //.paths(PathSelectors.regex("(?!/error).+"))//exclude path
                //.paths(PathSelectors.regex("/orders.*"))//include path
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Learning Swagger")
                .description("We're all gonna make it brahz <3")
                .version("1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0")
                .license("My license...?")
                .licenseUrl("https://www.youtube.com/")
                .contact(new Contact("Pulson", "https://en.wikipedia.org/wiki/Main_Page", "something@gmail.com"))
                .build();
    }

//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI()
//                .info(new Info().title("Learning Swagger API")
//                    .description("Learning...")
//                    .version("v0.1")
//                    .license(new License().name("My OWN license <3")))
//                .externalDocs(new ExternalDocumentation()
//                    .description("External documentation").url("https://www.youtube.com/"));
//    }
}
