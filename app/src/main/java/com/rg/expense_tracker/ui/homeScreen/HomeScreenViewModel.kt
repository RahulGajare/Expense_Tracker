package com.rg.expense_tracker.ui.homeScreen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.expense_tracker.interfaces.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor
    (private val repo : IRepository) : ViewModel() {

    var initialAccountBalanceState = mutableStateOf("")
    var remainingAccountBalanceState = mutableStateOf("")
    var amountInPercentageState = mutableStateOf(0)
    var radioOptionsSelectedState = mutableStateOf("Debit")

    init {
        viewModelScope.launch{
            getActiveVisibleAccount()
            getExpensedPercentage(initialAccountBalanceState.value.toInt(),remainingAccountBalanceState.value.toInt())
        }

    }
   suspend fun getActiveVisibleAccount()
    {
        initialAccountBalanceState.value  = repo.getAccounts()[0].initialAccountBalance
        remainingAccountBalanceState.value  = repo.getAccounts()[0].remainingAccountBalance
    }

    fun getExpensedPercentage(initialValue : Int , remainingValue : Int)
    {
        amountInPercentageState.value = (remainingAccountBalanceState.value.toInt() * 100) / initialValue
    }
}