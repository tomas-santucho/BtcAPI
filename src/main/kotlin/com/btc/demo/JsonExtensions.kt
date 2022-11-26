package com.btc.demo

import com.btc.demo.model.FiatType
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.math.BigDecimal
import java.time.LocalDateTime

fun JsonObject.getFiatType() = when (this.get("code").asString) {
    FiatType.USD.name -> FiatType.USD
    FiatType.GBP.name -> FiatType.GBP
    FiatType.EUR.name -> FiatType.EUR
    else -> throw Exception()
}

fun JsonObject.getPrice() = BigDecimal(this.get("rate").asString.filterNot { it == ',' })

fun JsonElement.asLocalDateTime(): LocalDateTime {
    //val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+SS.SS").withZone(ZoneId.of("UTC"))
    //return LocalDateTime.parse(this.asString, formatter)
    return LocalDateTime.now()
}