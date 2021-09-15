package com.rg.expense_tracker.ui.country_currency


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rg.expense_tracker.models.CountryCurrency
import com.rg.expense_tracker.models.Currency
import java.util.*

@Composable
fun Currency_Select_Screen(navController: NavController) {

    val viewModel: CountryCurrenncyViewModel = hiltViewModel()

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar("Search")

            CurrencyList(viewModel = viewModel , navController = navController)
            Button(onClick = { /*TODO*/ },
            modifier = Modifier.weight(1f)) {
                Text(text = "Next")

            }


        }


    }
}

@Composable
fun SearchBar(hint: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        elevation = 8.dp,
        backgroundColor = Color.Cyan
    )
    {
        TextField(
            value = "", onValueChange = {},
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(25.dp))
                .height(50.dp),
            placeholder = { Text(text = hint, color = Color.Gray) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.onSecondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

    }
}

@Composable
fun CurrencyList(viewModel: CountryCurrenncyViewModel, navController: NavController) {

    val currencyList = viewModel.currencyListState.value

        LazyColumn()
        {
            items(currencyList)
            { item ->
                CurrencyItem(item)
                {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("selected_Currency", it.code)
                    navController.popBackStack()
                }
            }
        }

}

@Composable
fun CurrencyItem(countryCurrency: CountryCurrency , onItemClick : (currency : Currency) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()
        .clickable { onItemClick(countryCurrency.currency) }) {
        Row() {
            Text(
                text = countryCurrency.name,
                Modifier.weight(0.8F)
            )
            Text(
                text = countryCurrency.currency.code,
                Modifier.weight(0.1F)
            )
            
        }
        Divider(thickness = 2.dp)
        
       
    }

}

@Preview
@Composable
fun DefaultSearchBar() {
    SearchBar(hint = "Search")
}