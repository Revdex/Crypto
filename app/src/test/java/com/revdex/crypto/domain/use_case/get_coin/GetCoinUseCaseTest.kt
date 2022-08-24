package com.revdex.crypto.domain.use_case.get_coin

import com.google.common.truth.Truth.assertThat
import com.revdex.crypto.common.Resource
import com.revdex.crypto.data.repository.FakeCoinRepository
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCoinUseCaseTest {
    private lateinit var getGetCoinUseCase: GetCoinUseCase
    private lateinit var fakeCoinRepository: FakeCoinRepository

    @Before
    fun setUp() {
        fakeCoinRepository = FakeCoinRepository()
        getGetCoinUseCase = GetCoinUseCase(fakeCoinRepository)
    }

    @Test
    fun shouldReturnResultWhenValidCoinIdIsPassed() = runBlocking {
        val coinId = fakeCoinRepository.getRandomCoinId()
        val coinUseCase = getGetCoinUseCase(coinId).last()
        assertThat((coinUseCase as Resource.Success).data?.coinId).isEqualTo(coinId)
    }

    @Test
    fun shouldReturnErrorWhenInValidCoinIdIsPassed() = runBlocking {
        val coinId = "InValidId"
        val coinUseCase = getGetCoinUseCase(coinId).last()
        assertThat(coinUseCase is Resource.Error).isTrue()
    }
}