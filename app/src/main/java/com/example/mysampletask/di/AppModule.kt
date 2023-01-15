package com.example.mysampletask.di

import com.example.mysampletask.data.services.RemoteService
import com.example.mysampletask.data.services.Repository
import com.example.mysampletask.data.usecases.ImageCollectionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteServices(): RemoteService = RemoteService()

    @Provides
    @Singleton
    fun provideRepositorySDK(remoteService: RemoteService): Repository =
        Repository(remoteService = remoteService)

    @Provides
    @Singleton
    fun imageCollectionUseCase(repository: Repository): ImageCollectionUseCase =
        ImageCollectionUseCase(repository)

}