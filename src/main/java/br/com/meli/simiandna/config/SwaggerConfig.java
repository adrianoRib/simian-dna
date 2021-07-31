package br.com.meli.simiandna.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
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
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()
        		.apis(RequestHandlerSelectors.basePackage("br.com.meli.simiandna"))
        		.paths(PathSelectors.any())
        		.build()
        		.groupName("version 1.0.0")
        		.apiInfo(generateApiInfo());
    }
    
    private ApiInfo generateApiInfo(){
        return new ApiInfoBuilder()
                .title("Simian DNA Check API")
                .description("Detecta se uma sequência de DNA pertence a um humano ou a um símio.")
                .version("v1")
        		.contact(new Contact("Adriano Ribeiro", "https://www.mercadolivre.com.br/", "adriano.ribeiro86@gmail.com"))
                .build();
    }
}