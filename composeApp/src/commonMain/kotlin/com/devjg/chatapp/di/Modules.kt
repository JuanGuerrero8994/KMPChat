package com.devjg.chatapp.di

import com.devjg.chatapp.data.remote.ChatApi
import com.devjg.chatapp.data.repository.auth.AuthRepositoryImpl
import com.devjg.chatapp.data.repository.auth.ChatRepositoryImpl
import com.devjg.chatapp.data.repository.auth.MessageRepositoryImpl
import com.devjg.chatapp.domain.repository.AuthRespository
import com.devjg.chatapp.domain.repository.ChatRepository
import com.devjg.chatapp.domain.repository.MessageRepository
import com.devjg.chatapp.domain.usecases.auth.AuthenticateUseCase
import com.devjg.chatapp.domain.usecases.chat.ChatUseCase
import com.devjg.chatapp.domain.usecases.message.MessageUseCase
import com.devjg.chatapp.ui.screen.auth.AuthViewModel
import com.devjg.chatapp.ui.screen.chat.ChatViewModel
import com.devjg.chatapp.ui.screen.message.MessageViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val dataModule = module {
    single { ChatApi() }
    single<AuthRespository> { AuthRepositoryImpl(get()) }
    single<MessageRepository> { MessageRepositoryImpl(get()) }
    single<ChatRepository> { ChatRepositoryImpl(get()) }
}

private val domainModule = module {
    factory { AuthenticateUseCase(get()) }
    factory { MessageUseCase(get()) }
    factory { ChatUseCase(get()) }
}


private val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { MessageViewModel(get()) }
    viewModel { ChatViewModel(get()) }
}


var sharedModules = listOf(domainModule, dataModule, viewModelModule)

