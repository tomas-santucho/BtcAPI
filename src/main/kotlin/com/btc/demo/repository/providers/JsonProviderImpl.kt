package com.btc.demo.repository.providers

import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader

private const val URL = "https://api.coindesk.com/v1/bpi/currentprice.json"


@Component
class JsonProviderImpl : JsonProvider {
    override fun getJson(): Json = java.net.URL(URL).openStream().use { input ->
        val isr = InputStreamReader(input)
        val reader = BufferedReader(isr)
        val json = StringBuilder()
        var c: Int
        while (reader.read().also { c = it } != -1) {
            json.append(c.toChar())
        }
        return Json(json.toString())
    }
}