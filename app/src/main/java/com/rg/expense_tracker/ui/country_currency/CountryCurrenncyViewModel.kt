package com.rg.expense_tracker.ui.country_currency

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.rg.expense_tracker.Repository.Repository
import com.rg.expense_tracker.interfaces.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountryCurrenncyViewModel @Inject constructor(
    private val repository: IRepository ,
    application: Application
): AndroidViewModel(application)
{
    init {
        getCountryCurriences()
    }

    fun getCountryCurriences()
    {
        repository.getCurrencies(this.getApplication())
    }

}