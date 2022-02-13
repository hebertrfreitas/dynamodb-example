package com.hebertrfreitas.dynamodbexample.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI


@Configuration
class DynamoDBConfig(val awsProperties: AwsProperties) {

    @Bean
    fun dynamoDBClient():DynamoDbClient{
        return DynamoDbClient.builder()
            .region(awsProperties.regionAsAwsEnum())
            .apply {
                if(! awsProperties.endpoint.isNullOrEmpty())
                    endpointOverride(URI(awsProperties.endpoint)) //awsProperties.endpoint = http://localhost:8000
            }
            .credentialsProvider(DefaultCredentialsProvider.builder().build())
            .build()
    }

    @Bean
    fun dynamoDBEnhancedClient(dynamoDbClient: DynamoDbClient): DynamoDbEnhancedClient{
        return DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build()
    }

}