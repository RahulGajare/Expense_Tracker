package com.rg.expense_tracker.ui.TransactionScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.rg.expense_tracker.models.localdata.SpentItem
import com.rg.expense_tracker.ui.homeScreen.HomeScreenViewModel

@Composable
fun TransactionScreen(navController: NavController)
{
    val viewModel: TransactionScreenViewModel = hiltViewModel()
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .navigationBarsWithImePadding()
        .statusBarsPadding()) {
        items(viewModel.transactionListState.value) { transactionItem ->
            TransactionItem(viewModel, transactionItem)
            Divider(modifier = Modifier.padding(vertical = 4.dp),
                    thickness = 2.dp)
        }
    }

}


@Composable
fun TransactionItem(viewModel : TransactionScreenViewModel, transactionItem: SpentItem ) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween)
    {
        Column(modifier = Modifier
            .weight(0.7f),
            horizontalAlignment = Alignment.Start)
        {
            Text(text = transactionItem.description,
                style = MaterialTheme.typography.h1
            )
            Text(modifier = Modifier.padding(top = 8.dp),
                text = transactionItem.dateTime,
                style = MaterialTheme.typography.h1)
        }
        Column(modifier = Modifier
            .weight(0.3f),
            horizontalAlignment = Alignment.End)
        {
            Text(text = transactionItem.spentAmount,
                style = MaterialTheme.typography.h1)
        }

    }
}