package br.com.justice.application.configuration;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public Docket api(@Value("${springdoc.version}") String appVersion,
                    @Value("${springdoc.title}") String title,
                    @Value("${springdoc.terms}") String terms,
                    @Value("${springdoc.contact}") String contact,
                    @Value("${springdoc.name}") String name,
                    @Value("${springdoc.email}") String email,
                    @Value("${springdoc.license}") String license,
                    @Value("${springdoc.url}") String url
  ) {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("br.com.justice"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(
            apiInfo(
                appVersion, title, terms, contact, name, email, license, url
            )
        );

  }

  ApiInfo apiInfo(String appVersion,
                  String title,
                  String terms,
                  String contact,
                  String name,
                  String email,
                  String license,
                  String url) {
    return new ApiInfo(
        title,
        "Mapping of justice endpoints",
        appVersion,
        terms,
        new Contact(name, contact, email),
        license,
        url,
        Collections.emptyList());
  }
}
