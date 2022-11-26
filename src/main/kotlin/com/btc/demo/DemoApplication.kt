package com.btc.demo

import com.btc.demo.repository.RepositoryExternalImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    RepositoryExternalImpl().fetchCurrentPrice()
    runApplication<DemoApplication>(*args)
}
