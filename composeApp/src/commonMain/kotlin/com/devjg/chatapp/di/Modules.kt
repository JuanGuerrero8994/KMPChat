package com.devjg.chatapp.di

import com.devjg.chatapp.data.remote.ChatApi
import com.devjg.chatapp.data.repository.auth.AuthRepositoryImpl
import com.devjg.chatapp.domain.repository.AuthRespository
import com.devjg.chatapp.domain.usecases.AuthenticateUseCase
import com.devjg.chatapp.ui.screen.auth.AuthViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val dataModule = module {
    single { ChatApi() }
    single<AuthRespository> { AuthRepositoryImpl(get()) }
}

private val domainModule = module {
    factory { AuthenticateUseCase(get()) }
}


private val viewModelModule = module {
    viewModel { AuthViewModel(get())}

}


var sharedModules = listOf(domainModule, dataModule, viewModelModule)

