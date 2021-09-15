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
   suspend fun addAccount(userAccount: UserAccount)

    @Query("select * from UserAccount")
   suspend fun getAccounts(): List<UserAccount>

}