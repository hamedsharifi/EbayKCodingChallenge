package com.ebayk.core.usecase

import com.ebayk.core.repository.AdRepository

class GetAdDetailUseCase(private val repository: AdRepository) {

    suspend fun execute(adId: String) = repository.getAdDetail(adId)
}