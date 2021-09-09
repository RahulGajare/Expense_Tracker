package com.rg.expense_tracker.di

import com.rg.expense_tracker.Repository.Repository
import com.rg.expense_tracker.interfaces.IRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository (repo : Repository) : IRepository
    {
        return Repository()
    }
}