package com.hebertrfreitas.dynamodbexample.model


import com.fasterxml.jackson.annotation.JsonFormat
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.time.LocalDate


/**
 * Important note: using `var` because the AWS SDK needs default `setters` in order to detect an attribute
 */
@DynamoDbBean
class Painter(
    @get:DynamoDbPartitionKey var id: String? = null,
    var name: String? = null,
    @field:JsonFormat(pattern="yyyy-MM-dd")
    var birthdate: LocalDate? = null,
    var paintings:List<Paint> = mutableListOf()
){
    constructor():this(null, null, null)
}



@DynamoDbBean
class Paint(var name:String?, var location:String?){
    constructor():this(null, null)
}

