package com.dc.framework.config;

import com.dc.common.constant.CustomConstant;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@EnableOpenApi
@Configuration
public class SwaggerConfig {
    @Value("${sms.swagger.enable}")
    private boolean enable;
    @Value("${sms.swagger.title}")
    private String title;
    @Value("${sms.swagger.description}")
    private String description;
    @Value("${sms.swagger.name}")
    private String name;
    @Value("${sms.swagger.url}")
    private String url;
    @Value("${sms.swagger.email}")
    private String email;
    @Value("${sms.swagger.version}")
    private String version;
    @Value("${sms.swagger.protocols}")
    private String[] protocols;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                //支持的通讯协议集合
                .protocols(new HashSet<>(Arrays.asList(protocols)))
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList();
        apiKeyList.add(new ApiKey(CustomConstant.AUTHORIZATION, CustomConstant.AUTHORIZATION, "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{
                new AuthorizationScope("global", "accessEverything")
        };
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference(CustomConstant.AUTHORIZATION, authorizationScopes));
        return securityReferences;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .contact(new Contact(name, url, email))
                .version(version)
                .build();
    }


}
