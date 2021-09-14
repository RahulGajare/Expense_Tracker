package com.rg.expense_tracker.ui.addAccount

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rg.expense_tracker.interfaces.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val Repo : IRepository
) : ViewModel() {

    val accountNameState = mutableStateOf("")
    val accountBalanceState = mutableStateOf("")
    val currencyState = mutableStateOf("")
}