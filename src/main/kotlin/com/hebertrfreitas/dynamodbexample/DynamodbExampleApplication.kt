package com.hebertrfreitas.dynamodbexample

import com.hebertrfreitas.dynamodbexample.config.AwsProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class DynamodbExampleApplication

fun main(args: Array<String>) {
	runApplication<DynamodbExampleApplication>(*args)
}
