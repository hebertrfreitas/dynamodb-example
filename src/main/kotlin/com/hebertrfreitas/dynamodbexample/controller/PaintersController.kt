package com.hebertrfreitas.dynamodbexample.controller

import com.hebertrfreitas.dynamodbexample.model.Painter
import com.hebertrfreitas.dynamodbexample.repository.PaintersRepository
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/painters")
class PaintersController(val paintersRepository: PaintersRepository) {

    val logger = LoggerFactory.getLogger(this::class.java)


    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createPainter(@RequestBody painter: Painter):ResponseEntity<Any>{
        return try {
            paintersRepository.createPainter(painter)
            ResponseEntity.ok().build()
        }catch (e: IllegalArgumentException){
            ResponseEntity.badRequest().body(e.message.orEmpty())
        }

    }

    @GetMapping("{id}")
    fun getPainter(@PathVariable id: String):ResponseEntity<Any> {
        paintersRepository.getPainter(id)?.let { return ResponseEntity.ok(it) }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("{id}")
    fun deletePainter(@PathVariable id: String):ResponseEntity<Any> {
        paintersRepository.getPainter(id)?.let {
            paintersRepository.deletePainter(it)
            return ResponseEntity.noContent().build()
        }
        return ResponseEntity.notFound().build()
    }
}