package com.revdex.crypto.data.repository

import com.revdex.crypto.data.remote.CoinPaprikaApi
import com.revdex.crypto.data.remote.dto.toCoin
import com.revdex.crypto.data.remote.dto.toCoinDetail
import com.revdex.crypto.domain.model.Coin
import com.revdex.crypto.domain.model.CoinDetail
import com.revdex.crypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<Coin> {
        return api.getCoins().map { it.toCoin() }
    }

    override suspend fun getCoinById(coinId: String): CoinDetail {
        return api.getCoinById(coinId).toCoinDetail()
    }
}