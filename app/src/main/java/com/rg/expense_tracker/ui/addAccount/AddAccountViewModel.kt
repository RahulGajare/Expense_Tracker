package com.rg.expense_tracker.ui.addAccount

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.expense_tracker.interfaces.IRepository
import com.rg.expense_tracker.models.localdata.UserAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val repo : IRepository
) : ViewModel() {

    val accountNameState = mutableStateOf("")
    val accountBalanceState  = mutableStateOf("")
    val currencyState = mutableStateOf("")


    fun addNewAccount()
    {
        viewModelScope.launch {
            repo.addUserAccount(UserAccount(accountName = accountNameState.value,
                accountBalance = accountBalanceState.value,
                currencyType = currencyState.value))
        }

    }

    fun checkValidAmount(selectedValue : Int , addSub : Int) : Boolean
    {
        if(accountBalanceState.value.isEmpty() && addSub == 1)
        {
            accountBalanceState.value =  selectedValue.toString()
        }
        else if (addSub ==0 && accountBalanceState.value.toInt() - selectedValue >= 0)
        {
            accountBalanceState.value = (accountBalanceState.value.toInt() - selectedValue).toString()
            return true
        }
        else if(addSub ==1)
        {
            accountBalanceState.value =  (accountBalanceState.value.toInt() + selectedValue).toString()
            return true
        }
        return false;
    }
}