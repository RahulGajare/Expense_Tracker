package com.rg.expense_tracker.Repository

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rg.expense_tracker.interfaces.IRepository
import com.rg.expense_tracker.models.CountryCurrencies
import com.rg.expense_tracker.models.localdata.UserAccount
import com.rg.expense_tracker.models.resource.Resource
import com.rg.expense_tracker.room.UserAccountDao
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(private val userAccountDao: UserAccountDao) : IRepository {
    override fun getCurrencies(application: Application): Resource<CountryCurrencies> {
        val jsonString: String
        try {
            jsonString =
                application.assets.open("countries.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return Resource.Error("IO Exception")
        }

        val gson = Gson()
        val countryCurrencyType = object : TypeToken<CountryCurrencies>() {}.type

        var countryCurrency: CountryCurrencies = gson.fromJson(jsonString, countryCurrencyType)

        return Resource.Success(countryCurrency)
    }

    fun addUserAccount(userAccount: UserAccount) {
        userAccountDao.addUser(userAccount = userAccount)
    }
}