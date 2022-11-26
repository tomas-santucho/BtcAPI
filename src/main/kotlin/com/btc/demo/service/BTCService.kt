package com.btc.demo.service

import com.btc.demo.exceptions.PairNotFoundException
import com.btc.demo.model.BTCPrice
import com.btc.demo.model.FiatType
import com.btc.demo.repository.BTCRepository
import com.btc.demo.repository.RepositoryExternal
import org.springframework.stereotype.Service

@Service
class BTCService(private val repository: BTCRepository, private val repositoryExternal: RepositoryExternal) : UseCases {
    override fun fetchAll(): List<BTCPrice> = repository.findAll()

    override fun fetchBtcPriceByFiat(type: FiatType): BTCPrice =
        repository.findFirstByFiatTypeOrderByIdDesc(type).orElseThrow { PairNotFoundException() }

    override fun syncBtcPrice() {
        repository.saveAll(repositoryExternal.fetchCurrentPrice())
    }
}