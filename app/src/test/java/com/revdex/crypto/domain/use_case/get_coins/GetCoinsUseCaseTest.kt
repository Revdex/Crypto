package com.revdex.crypto.domain.use_case.get_coins

import com.google.common.truth.Truth.assertThat
import com.revdex.crypto.common.Resource
import com.revdex.crypto.data.repository.FakeCoinRepository
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

internal class GetCoinsUseCaseTest {

    private lateinit var getCoinsUseCase: GetCoinsUseCase
    private lateinit var fakeCoinRepository: FakeCoinRepository

    @Before
    fun setUp() {
        fakeCoinRepository = FakeCoinRepository()
        getCoinsUseCase = GetCoinsUseCase(fakeCoinRepository)
    }

    @Test
    fun shouldReturnErrorWhenExceptionIsThrown() = runBlocking {
        fakeCoinRepository.setShouldReturnException(true, IOException())
        val coinsUseCase = getCoinsUseCase().last()
        assertThat(coinsUseCase is Resource.Error).isTrue()
    }



    @Test
    fun shouldReturnResultWhenNoExceptionIsThrown() = runBlocking {
        val coinsUseCase = getCoinsUseCase().last()
        assertThat((coinsUseCase as Resource.Success).data).isNotNull()

    }

}