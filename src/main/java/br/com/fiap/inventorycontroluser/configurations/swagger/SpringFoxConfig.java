package br.com.fiap.inventorycontroluser.configurations.swagger;

import br.com.fiap.inventorycontroluser.domains.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@EnableSwagger2
@Configuration
public class SpringFoxConfig implements WebMvcConfigurer {

  @Value("${springfox.pathMapping:/}")
  private String springFoxPathMapping;

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.OAS_30)
        .apiInfo(getApiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("br.com.fiap.inventorycontroluser"))
        .paths(PathSelectors.any())
        .build()
        .pathMapping(springFoxPathMapping)
        .ignoredParameterTypes(User.class);
  }

  private ApiInfo getApiInfo() {
    return new ApiInfoBuilder()
        .title("API Inventory Control User")
        .description("Inventory Control User Application")
        .version("1.0.0")
        .build();
  }
}
