package member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
    	ApiInfoBuilder apiInfo = new ApiInfoBuilder();
    	apiInfo.title("회원가입 API 서버 문서");
    	apiInfo.description("회원가입 API 서버 문서입니다.");
    	
    	Docket docket = new Docket(DocumentationType.SWAGGER_2);
    	docket.apiInfo(apiInfo.build());
    	
    	ApiSelectorBuilder apis = docket.select().apis(RequestHandlerSelectors.basePackage("member.controller"));
    	apis.paths(PathSelectors.ant("/**"));
    	
    	return apis.build();
    }
}