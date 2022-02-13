package com.hebertrfreitas.dynamodbexample.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import software.amazon.awssdk.regions.Region


@ConstructorBinding
@ConfigurationProperties(prefix = "aws")
data class AwsProperties(val region: String? , val endpoint:String?){

    val logger = LoggerFactory.getLogger(this::class.java)

    fun regionAsAwsEnum(): Region {
        return try {
            Region.of(region)
        }catch (e: Exception) {
            logger.warn("The specified region property does no math with any AWS region, " +
                    "started with default region. [specified-region=$region]")
            Region.SA_EAST_1
        }
    }

}
