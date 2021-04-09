package com.example.catalogservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "polar")
data class PolarProperties(/* A message to welcome users */ val greeting: String) {
}