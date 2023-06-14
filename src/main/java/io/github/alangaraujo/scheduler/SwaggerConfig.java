package io.github.alangaraujo.scheduler;

import org.springframework.beans.factory.annotation.Value;
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
	
    @Value("${build.version:1.0.0-SNAPSHOT}")
    private String buildVersion;
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        	.useDefaultResponseMessages(false)
        	.select()
        	.apis(RequestHandlerSelectors.basePackage("io.github.alangaraujo"))
        	.paths(PathSelectors.any())
        	.build()
                .apiInfo(buildApiInfo());
    }
    
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
            .title("Message notifier API")
            .description("Message scheduling for notifications")
            .version(buildVersion)
            .contact(new Contact("Alan Araújo",
                    "https://www.linkedin.com/in/alangaraujo/", "alangaraujo@gmail.com"))
            .build();
    }
}
