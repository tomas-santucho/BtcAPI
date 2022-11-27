package com.btc.demo

import com.btc.demo.model.FiatType
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


fun JsonObject.getFiatType() = when (this.get("code").asString) {
    FiatType.USD.name -> FiatType.USD
    FiatType.GBP.name -> FiatType.GBP
    FiatType.EUR.name -> FiatType.EUR
    else -> throw Exception()
}

fun JsonObject.getPrice() = BigDecimal(this.get("rate").asString.filterNot { it == ',' })

fun JsonElement.asLocalDateTime(): LocalDateTime {
    val timeFormatter = DateTimeFormatter.ISO_DATE_TIME
    val accessor = timeFormatter.parse(this.asString)
    return LocalDateTime.ofInstant(Instant.from(accessor), ZoneId.of("UTC"))
}