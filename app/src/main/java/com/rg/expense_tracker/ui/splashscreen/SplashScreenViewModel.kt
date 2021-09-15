package com.rg.expense_tracker.ui.splashscreen

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.expense_tracker.interfaces.IRepository
import com.rg.expense_tracker.models.localdata.UserAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val repo: IRepository) : ViewModel() {

    suspend fun checkAtleastOneAccountExist(): Boolean {

        val accounts = repo.getAccounts()
        return accounts.isNotEmpty()


    }

}
