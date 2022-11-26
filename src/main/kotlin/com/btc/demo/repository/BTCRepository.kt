package com.btc.demo.repository

import com.btc.demo.model.BTCPrice
import com.btc.demo.model.FiatType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BTCRepository : JpaRepository<BTCPrice, Long> {
    fun findFirstByFiatTypeOrderByIdDesc(fiatType: FiatType): Optional<BTCPrice>
}