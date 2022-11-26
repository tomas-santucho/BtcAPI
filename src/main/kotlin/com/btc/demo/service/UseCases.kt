package com.btc.demo.service

import com.btc.demo.model.FiatType
import com.btc.demo.model.BTCPrice

interface UseCases {
    fun fetchAll(): List<BTCPrice>
    fun fetchBtcPriceByFiat(type: FiatType): BTCPrice
    fun syncBtcPrice()
}