package com.boehle.cmdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Bean
    public Docket v2Documentation() {
        return new VersionDocket("v2","v2");
    }

    @Bean
    public Docket v1Documentation() {
        return new VersionDocket("v1","v1");
    }
    
    @Bean
    public Docket latestDocumentation() {
        return new VersionDocket("latest", "((?!v1|v2).)*$");
    }
    
	class VersionDocket extends Docket {
		public VersionDocket(String version, String vRegex) {
			super(DocumentationType.SWAGGER_2);
			super.groupName(version)
			.select()
			.apis(RequestHandlerSelectors
			.basePackage("com.boehle.cmdb.controller"))
			.paths(PathSelectors.regex("/api/" + vRegex + ".*"))
			.build().apiInfo(apiEndPointsInfo(version));
		}
	
		private ApiInfo apiEndPointsInfo(String version) {
			return new ApiInfoBuilder().title("CMDB RestApi")
					.description("Chuck Movie Database REST API")
					.version(version)
					.build();
		}
	}

}
