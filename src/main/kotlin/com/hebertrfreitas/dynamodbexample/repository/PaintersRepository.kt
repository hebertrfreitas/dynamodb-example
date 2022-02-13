package com.hebertrfreitas.dynamodbexample.repository

import com.hebertrfreitas.dynamodbexample.model.Painter
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest


@Repository
class PaintersRepository(val dynamoDbEnhancedClient: DynamoDbEnhancedClient) {


    private fun getMappedTable(): DynamoDbTable<Painter> {
        return dynamoDbEnhancedClient.table("Painters", TableSchema.fromBean(Painter::class.java))
    }


    fun createPainter(painter: Painter){

        getPainter(requireNotNull(painter.id))?.run {
            throw IllegalArgumentException("A painter with this id already exists [id=${painter.id}]")
        }

        val putItemRequest = PutItemEnhancedRequest.builder(Painter::class.java).item(painter).build()
        getMappedTable().putItem(putItemRequest)
    }

    fun getPainter(painterId: String): Painter?{

        val getItemRequest = GetItemEnhancedRequest.builder()
           .key(Key.builder()
               .partitionValue(painterId)
               .build()
           ).build()
       return getMappedTable().getItem(getItemRequest)
    }

    fun deletePainter(painter: Painter) {
        requireNotNull(painter.id)
        val deleteItemRequest = DeleteItemEnhancedRequest.builder()
            .key(Key.builder()
                .partitionValue(painter.id)
                .build()
            ).build()
         getMappedTable().deleteItem(deleteItemRequest)

    }

}