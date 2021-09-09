package com.rg.expense_tracker.interfaces

import android.app.Application
import com.rg.expense_tracker.models.Country_Currency

interface IRepository {

    fun getCurrencies(application: Application) : Country_Currency?

}