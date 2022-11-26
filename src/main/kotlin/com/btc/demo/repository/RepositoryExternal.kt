package com.btc.demo.repository

import com.btc.demo.model.BTCPrice
import org.springframework.stereotype.Repository

@Repository
interface RepositoryExternal {
    fun fetchCurrentPrice(): List<BTCPrice>
}