package com.rg.expense_tracker.ui.country_currency


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
import com.rg.expense_tracker.models.CountryCurrency
import java.util.*

@Composable
fun Currency_Select_Screen() {

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
            CurrencyList(viewModel = viewModel)
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
                backgroundColor = Color.Red,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

    }
}

@Composable
fun CurrencyList(viewModel: CountryCurrenncyViewModel) {

    val currencyList = viewModel.currencyListState.value
    LazyColumn()
    {
        items(currencyList)
        { item ->
            CurrencyItem(item)
        }
    }

}

@Composable
fun CurrencyItem(countryCurrenncy: CountryCurrency) {
    Row {
        Text(
            text = countryCurrenncy.name,
            Modifier.weight(0.8F)
        )
        Text(
            text = countryCurrenncy.currency.code,
            Modifier.weight(0.1F)
        )
      

    }
}

@Preview
@Composable
fun DefaultSearchBar() {
    SearchBar(hint = "Search")
}