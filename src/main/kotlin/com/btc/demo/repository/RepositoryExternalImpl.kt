package com.btc.demo.repository

import com.btc.demo.asLocalDateTime
import com.btc.demo.getFiatType
import com.btc.demo.getPrice
import com.btc.demo.model.BTCPrice
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.springframework.stereotype.Repository
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


private const val URL = "https://api.coindesk.com/v1/bpi/currentprice.json"

@Repository
class RepositoryExternalImpl : RepositoryExternal {

    object Keys {
        const val BPI = "bpi"
        const val TIME = "time"
        const val ISO = "updatedISO"
        const val USD = "USD"
        const val GBP = "GBP"
        const val EUR = "EUR"
    }

    override fun fetchCurrentPrice(): List<BTCPrice> {
        val jsonObject: JsonObject = JsonParser.parseString(getJson()).asJsonObject
        val bpi = jsonObject.getAsJsonObject(Keys.BPI)
        val time = jsonObject.getAsJsonObject(Keys.TIME).get(Keys.ISO).asLocalDateTime()
        val usd = bpi.getAsJsonObject(Keys.USD)
        val gbp = bpi.getAsJsonObject(Keys.GBP)
        val eur = bpi.getAsJsonObject(Keys.EUR)
        return listOf(
            BTCPrice(null, usd.getPrice(), time, usd.getFiatType()),
            BTCPrice(null, gbp.getPrice(), time, gbp.getFiatType()),
            BTCPrice(null, eur.getPrice(), time, eur.getFiatType())
        )
    }

    private fun getJson(): String = URL(URL).openStream().use { input ->
        val isr = InputStreamReader(input)
        val reader = BufferedReader(isr)
        val json = StringBuilder()
        var c: Int
        while (reader.read().also { c = it } != -1) {
            json.append(c.toChar())
        }
        return json.toString()
    }
}