package com.btc.demo.controller

import com.btc.demo.exceptions.PairNotFoundException
import com.btc.demo.model.FiatType
import com.btc.demo.service.BTCService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/btc")
class BTCController(private val service: BTCService) {
    val logger = LoggerFactory.getLogger(BTCController::class.java)

    @GetMapping("/")
    fun get(): ResponseEntity<Any> = try {
        ResponseEntity.ok(service.fetchAll())
    } catch (e: Exception) {
        logger.error(e.message)
        ResponseEntity.internalServerError().build()
    }

    @GetMapping("/pair/{fiatType}")
    fun getByFiatType(@PathVariable fiatType: FiatType): ResponseEntity<Any> = try {
        ResponseEntity.ok(service.fetchBtcPriceByFiat(fiatType))
    } catch (e: PairNotFoundException) {
        ResponseEntity.ok("Pair not found, Try syncing.")
    } catch (e: Exception) {
        logger.error(e.message)
        ResponseEntity.internalServerError().build()
    }

    @PostMapping("/")
    fun post(): ResponseEntity<String> = try {
        service.syncBtcPrice()
        ResponseEntity.created(URI("localhost:8080/btc/")).build()
    } catch (e: Exception) {
        logger.error(e.message)
        ResponseEntity.internalServerError().build()
    }
}