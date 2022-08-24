package com.revdex.crypto.domain.repository

import com.revdex.crypto.domain.model.Coin
import com.revdex.crypto.domain.model.CoinDetail

interface CoinRepository {

    suspend fun getCoins(): List<Coin>

    suspend fun getCoinById(coinId: String): CoinDetail
}