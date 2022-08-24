package com.revdex.crypto.data.repository

import com.revdex.crypto.domain.model.Coin
import com.revdex.crypto.domain.model.CoinDetail
import com.revdex.crypto.domain.repository.CoinRepository
import kotlin.random.Random


class FakeCoinRepository : CoinRepository {

    private var shouldReturnException: Boolean = false
    private var exception: Exception = Exception()


    private val coins = mutableListOf<Coin>().apply {
        add(Coin(id = "sngls-singulardtv", isActive = true, name = "SingularDTV", rank = 1310, symbol = "SNGLS"))
        add(Coin(id = "keyfi-keyfi", isActive = true, name = "KeyFi", rank = 1312, symbol = "KEYFI"))
        add(Coin(id = "edg-edgeless", isActive = true, name = "Edgeless", rank = 1314, symbol = "EDG"))
        add(Coin(id = "zer-zero", isActive = true, name = "Zero", rank = 1315, symbol = "ZER"))
        add(
            Coin(
                id = "bc-bitcoin-confidential",
                isActive = true,
                name = "Bitcoin",
                rank = 1311,
                symbol = "BC"
            )
        )
        add(
            Coin(
                id = "corgib-the-corgi-of-polkabridge",
                isActive = true,
                name = "The Corgi of PolkaBridge",
                rank = 1316,
                symbol = "CORGIB"
            )
        )
        add(
            Coin(
                id = "1177-sino-biopharmeceutical",
                isActive = true,
                name = "Sino Biopharmeceutical",
                rank = 1317,
                symbol = "1177"
            )
        )
    }


    fun setShouldReturnException(value: Boolean, exception: Exception) {
        this.shouldReturnException = value
        this.exception = exception
    }

    fun getRandomCoinId() = coins[Random.nextInt(coins.size)].id

    override suspend fun getCoins(): List<Coin> {
        if (shouldReturnException) {
            throw exception
        }
        return coins
    }

    override suspend fun getCoinById(coinId: String): CoinDetail {
        if (shouldReturnException) {
            throw exception
        }
        return coins
            .filter { it.id == coinId }
            .map { coin ->
                CoinDetail(
                    coinId = coin.id, name = coin.name,
                    symbol = coin.symbol,
                    rank = coin.rank,
                    isActive = coin.isActive,
                    team = emptyList(),
                    tags = listOf(
                        "Segwit",
                        "Cryptocurrency",
                        "Proof Of Work",
                        "Payments",
                        "Sha256",
                        "Mining",
                        "Lightning Network"
                    ),
                    description = "Bitcoin is a cryptocurrency and worldwide payment system." +
                            " It is the first decentralized digital currency," +
                            " as the system works without a central bank or single administrator.",
                )
            }.single()
    }

}