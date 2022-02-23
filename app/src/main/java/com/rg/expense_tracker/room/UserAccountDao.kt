package com.rg.expense_tracker.room

import androidx.room.*
import com.rg.expense_tracker.models.localdata.UserAccount
import kotlinx.coroutines.flow.Flow


@Dao
interface UserAccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addAccount(userAccount: UserAccount)  : Long

    @Query("select * from UserAccount")
   suspend fun getAccounts(): List<UserAccount>

    @Update
    suspend fun updateAccount(userAccount: UserAccount)

}