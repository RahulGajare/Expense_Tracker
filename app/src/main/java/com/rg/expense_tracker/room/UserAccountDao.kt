package com.rg.expense_tracker.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rg.expense_tracker.models.localdata.UserAccount
import kotlinx.coroutines.flow.Flow


@Dao
interface UserAccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(userAccount: UserAccount)

    @Query("select * from UserAccount")
    fun getUsers(): Flow<List<UserAccount>>

}