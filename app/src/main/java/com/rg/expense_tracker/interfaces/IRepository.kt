package com.rg.expense_tracker.interfaces

import android.app.Application
import androidx.lifecycle.LiveData
import com.rg.expense_tracker.models.CountryCurrencies
import com.rg.expense_tracker.models.localdata.UserAccount
import com.rg.expense_tracker.models.resource.Resource
import kotlinx.coroutines.flow.Flow

interface IRepository {

   fun getCurrencies(application: Application) : Resource<CountryCurrencies>
    suspend fun addUserAccount(userAccount: UserAccount)

    suspend fun getAccounts() : List<UserAccount>

}