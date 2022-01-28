package com.rg.expense_tracker.ui.homeScreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.expense_tracker.interfaces.IRepository
import com.rg.expense_tracker.models.localdata.SpentItems
import com.rg.expense_tracker.models.localdata.UserAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Handler
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class HomeScreenViewModel @Inject constructor
    (private val repo : IRepository) : ViewModel() {
    lateinit var activeAccount : UserAccount
    var walletNameState = mutableStateOf("")
    var initialAccountBalanceState = mutableStateOf("")
    var remainingAccountBalanceState = mutableStateOf("")
    var spentAccountBalanceState = mutableStateOf("")
    var amountInPercentageState = mutableStateOf(0)
    var radioOptionsSelectedState = mutableStateOf("Debit")
    var speakToTextState = mutableStateOf("")
    var transactionDescriptionState = mutableStateOf("")
    var transactionAmountState = mutableStateOf("")
    var transactionCardVisibility = mutableStateOf(false)

    init {
        viewModelScope.launch{
            getActiveVisibleAccount()
            getExpensedPercentage(initialAccountBalanceState.value.toInt(),remainingAccountBalanceState.value.toInt())
        }

    }
   suspend fun getActiveVisibleAccount()
    {
        activeAccount = repo.getAccounts()[0]
        walletNameState.value = activeAccount.accountName
        initialAccountBalanceState.value  = activeAccount.initialAccountBalance
        remainingAccountBalanceState.value  = activeAccount.remainingAccountBalance
        spentAccountBalanceState.value = (initialAccountBalanceState.value.toInt() - remainingAccountBalanceState.value.toInt()).toString()
    }

    fun getExpensedPercentage(initialValue : Int , remainingValue : Int)
    {
        amountInPercentageState.value = (remainingAccountBalanceState.value.toInt() * 100) / initialValue
    }

     suspend fun createTransaction() : Boolean
    {
        if(validateInput())
        {
            val newBalance =activeAccount.remainingAccountBalance.toInt() - transactionAmountState.value.toInt()
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())

            val spentItem = SpentItems(description = transactionDescriptionState.value,
                spentAmount = transactionAmountState.value,
                dateTime = currentDate)

            activeAccount.remainingAccountBalance = newBalance.toString()
            activeAccount.spendingList?.add(spentItem)
            repo.updateAccount(activeAccount)
            transactionDescriptionState.value = ""
            transactionAmountState.value = ""
            transactionCardVisibility.value = false
            viewModelScope.launch{
                getActiveVisibleAccount()
            }
            return true;
        }
        else
        {
            return false;
        }

    }


    fun validateInput() : Boolean
    {
        if(transactionDescriptionState.value.isNullOrEmpty() || transactionAmountState.value.isNullOrEmpty())
        {
            return false;
        }
        else if(!transactionAmountState.value.isDigitsOnly())
        {
            return false
        }
        return true;

    }

}