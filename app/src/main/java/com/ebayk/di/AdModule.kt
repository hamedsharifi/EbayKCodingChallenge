package com.ebayk.di

import com.ebayk.baseviewmodel.coroutineDispatcherProvider
import com.ebayk.core.repository.AdRepository
import com.ebayk.core.usecase.GetAdDetailUseCase
import com.ebayk.network.Services
import com.ebayk.presentation.AdViewModel
import com.ebayk.repository.AdRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val adModule = module {

    viewModel {
        AdViewModel(
            coroutineDispatcherProvider = coroutineDispatcherProvider(),
            get()
        )
    }

    single<Services> {
        Services.getInstance()
    }

    single<AdRepository> { AdRepositoryImpl(get()) }

    single {
        GetAdDetailUseCase(get())
    }
}