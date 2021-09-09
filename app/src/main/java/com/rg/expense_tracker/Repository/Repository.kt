package com.rg.expense_tracker.Repository

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rg.expense_tracker.interfaces.IRepository
import com.rg.expense_tracker.models.Country_Currency
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor  () : IRepository {
    override fun getCurrencies(application: Application): Country_Currency? {
        val jsonString: String
        try {
            jsonString = application.assets.open("countries.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        val gson = Gson()
        val countryCurrencyType = object : TypeToken<Country_Currency>() {}.type

        var country_Currency: Country_Currency = gson.fromJson(jsonString, countryCurrencyType)

        return country_Currency
    }
}