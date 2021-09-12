package com.rg.expense_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rg.expense_tracker.constants.Constants
import com.rg.expense_tracker.ui.SplashScreen
import com.rg.expense_tracker.ui.country_currency.CountryCurrenncyViewModel
import com.rg.expense_tracker.ui.country_currency.Currency_Select_Screen
import com.rg.expense_tracker.ui.homeScreen.HomeScreen
import com.rg.expense_tracker.ui.theme.Expense_TrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Expense_TrackerTheme {
                val viewModel : CountryCurrenncyViewModel = hiltViewModel()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ApplicationNavigation()
                }
            }
        }
    }
}

@Composable
fun ApplicationNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Constants.HOME_SCREEN)
    {

        composable(route = Constants.HOME_SCREEN)
        {
            HomeScreen()
        }
        composable(route = Constants.SPLASHSCREEN)
        {
            SplashScreen(navController = navController)
        }

        composable(route = Constants.CURRENCY_SELECT_SCREEN)
        {
            Currency_Select_Screen()
        }
    }
}
