package com.rg.expense_tracker.ui.TransactionScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.expense_tracker.interfaces.IRepository
import com.rg.expense_tracker.models.localdata.TransactionItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionScreenViewModel  @Inject constructor
    (private val repo : IRepository) : ViewModel(){
    var descriptionState = mutableStateOf("")
    var dateState = mutableStateOf("")
    var amountState = mutableStateOf("")
    var transactionListState = mutableStateOf(listOf<TransactionItem>())

    init {
        viewModelScope.launch() {getAccount()  }
    }


    private suspend  fun getAccount()
    {
        transactionListState.value = repo.getAccounts()[0].spendingList!!
        val s =""
    }

}