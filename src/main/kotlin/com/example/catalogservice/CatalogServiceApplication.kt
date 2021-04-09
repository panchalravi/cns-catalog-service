package com.example.catalogservice

import com.example.catalogservice.config.PolarProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.router

@SpringBootApplication
@EnableConfigurationProperties(PolarProperties::class)
class CatalogServiceApplication {

    @Bean
    fun routes(polarProperties: PolarProperties) = router {
        GET("/") {
            ok().body(polarProperties.greeting)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CatalogServiceApplication>(*args)
}


