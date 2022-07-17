package com.ebayk.core.repository

import com.ebayk.core.model.Ad


interface AdRepository {

    suspend fun getAdDetail(adId: String): Ad
}
