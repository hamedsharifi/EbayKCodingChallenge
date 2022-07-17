package com.ebayk.repository

import com.ebayk.core.model.Ad
import com.ebayk.core.repository.AdRepository
import com.ebayk.network.Services

class AdRepositoryImpl(private val retrofitService: Services): AdRepository {

    override suspend fun getAdDetail(adId: String): Ad {
        return retrofitService.getAd(adId)
    }
}