package com.rg.expense_tracker.ui.homeScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rg.expense_tracker.interfaces.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repo : IRepository

) : ViewModel() {

    var accountBalance = mutableStateOf("")

    suspend fun getActiveVisibleAccount()
    {
        accountBalance.value  = repo.getAccounts()[0].accountBalance
    }
}