package com.rg.expense_tracker.di

import android.content.Context
import androidx.room.Room
import com.rg.expense_tracker.Repository.Repository
import com.rg.expense_tracker.interfaces.IRepository
import com.rg.expense_tracker.room.UserAccountDao
import com.rg.expense_tracker.room.UserAccountRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository (repo : Repository, userAccountDao : UserAccountDao) : IRepository
    {
        return Repository(userAccountDao)
    }

    @Singleton
    @Provides
    fun provideUserAccountDao(userAccountRoomDataBase: UserAccountRoomDataBase): UserAccountDao {
        return userAccountRoomDataBase.userAccountDao()
    }

    @Provides
    @Singleton
    fun provideUserAccountRoomDataBase(@ApplicationContext appContext: Context): UserAccountRoomDataBase {
        return Room.databaseBuilder(
            appContext,
            UserAccountRoomDataBase::class.java,
            "UserAccount_database"
        ).build()
    }
}