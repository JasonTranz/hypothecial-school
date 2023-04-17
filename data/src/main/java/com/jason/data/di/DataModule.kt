package com.jason.data.di

import com.jason.data.repository.EventRepository
import com.jason.domain.repository.IEventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideEventRepository(dataSource: EventRepository): IEventRepository = dataSource
}