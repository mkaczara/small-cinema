package pl.mkaczara.smallcinema.common.rest.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun api(): Docket =
            Docket(DocumentationType.SWAGGER_2)
                    .useDefaultResponseMessages(false)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("pl.mkaczara"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(apiInfo())

    private fun apiInfo(): ApiInfo? =
            ApiInfo(
                    "Small-cinema REST API",
                    "REST API for small-cinema",
                    "0.1",
                    "",
                    Contact("Michal K.", "", "m@k.pl"),
                    "Apache License Version 2.0",
                    "https://www.apache.org/licenses/LICENSE-2.0",
                    emptyList()
            )

}