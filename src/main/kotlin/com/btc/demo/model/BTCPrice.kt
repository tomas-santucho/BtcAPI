package com.btc.demo.model

import jakarta.persistence.*
import org.hibernate.Hibernate
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class BTCPrice (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: Long? = null,
    val price: BigDecimal,
    val lastUpdate: LocalDateTime,
    @Enumerated(EnumType.STRING)
    val fiatType: FiatType
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as BTCPrice

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , price = $price , lastUpdate = $lastUpdate , fiatType = $fiatType )"
    }
}