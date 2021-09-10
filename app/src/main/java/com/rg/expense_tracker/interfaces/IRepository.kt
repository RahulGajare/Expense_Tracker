package com.rg.expense_tracker.interfaces

import android.app.Application
import com.rg.expense_tracker.models.CountryCurrencies
import com.rg.expense_tracker.models.resource.Resource

interface IRepository {

    fun getCurrencies(application: Application) : Resource<CountryCurrencies>

}