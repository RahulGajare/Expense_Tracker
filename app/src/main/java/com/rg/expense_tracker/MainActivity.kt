package com.rg.expense_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.rg.expense_tracker.constants.Constants
import com.rg.expense_tracker.ui.SplashScreen
import com.rg.expense_tracker.ui.TransactionScreen.TransactionScreen
import com.rg.expense_tracker.ui.addAccount.AddAccountScreen
import com.rg.expense_tracker.ui.country_currency.CountryCurrenncyViewModel
import com.rg.expense_tracker.ui.country_currency.Currency_Select_Screen
import com.rg.expense_tracker.ui.homeScreen.HomeScreen
import com.rg.expense_tracker.ui.homeScreen.HomeScreenNew
import com.rg.expense_tracker.ui.theme.Expense_TrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ProvideWindowInsets()
            {  Expense_TrackerTheme {
                val viewModel : CountryCurrenncyViewModel = hiltViewModel()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ApplicationNavigation()
                }
            }}

        }
    }
}

@Composable
fun ApplicationNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Constants.SPLASHSCREEN)
    {

        composable(route = Constants.HOME_SCREEN)
        {
            HomeScreenNew(navController)
        }
        composable(route = Constants.SPLASHSCREEN)
        {
            SplashScreen(navController = navController)
        }

        composable(route = Constants.CURRENCY_SELECT_SCREEN)
        {
            Currency_Select_Screen(navController = navController)
        }

        composable(route = Constants.ADD_ACCOUNT_SCREEN)
        {
            AddAccountScreen(navController = navController)
        }

        composable(route = Constants.TRANSACTION_SCREEN)
        {
            TransactionScreen(navController = navController)
        }


    }
}
