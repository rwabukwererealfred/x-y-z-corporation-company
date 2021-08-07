package com.NotificationRequest.configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	
	@Bean
    public Docket apis() { 
        return new Docket(DocumentationType.SWAGGER_2).groupName("Driver").apiInfo(metadata())
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com"))              
          .paths(PathSelectors.regex("/api/notification/.*"))                        
          .build();                                           
    }

	

	private ApiInfo metadata() {
	    return new ApiInfoBuilder()
	            .title( "X,Y,Z CORPORATION COMPANY" )
	            .description( "Spring boot backend Apis" ).contact(new springfox.documentation.service.Contact("Rwabukwerere Alfred",
	            		"https://github.com/rwabukwererealfred", "ralfred18404@gmail.com"))
	            .version( "1.0" )
	            .build();
	}
}
