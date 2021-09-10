package com.rg.expense_tracker.ui.country_currency

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.rg.expense_tracker.interfaces.IRepository
import com.rg.expense_tracker.models.CountryCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountryCurrenncyViewModel @Inject constructor(
    private val repository: IRepository ,
    application: Application
): AndroidViewModel(application)
{

    val currencyListState = mutableStateOf<List<CountryCurrency>>(emptyList())
    init {
        getCountryCurriences()
    }

    fun getCountryCurriences()
    {
        currencyListState.value =  repository.getCurrencies(this.getApplication()).data?.CountryCurrency!!

    }

}