package com.rg.expense_tracker.ui.TransactionScreen

import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.rg.expense_tracker.R
import com.rg.expense_tracker.constants.Constants
import com.rg.expense_tracker.models.localdata.TransactionItem

@Composable
fun TransactionScreen(navController: NavController) {
    val viewModel: TransactionScreenViewModel = hiltViewModel()
    Scaffold(modifier = Modifier
        .background(color = Color.Transparent)
        .fillMaxSize()
        .navigationBarsWithImePadding()
        .statusBarsPadding(),
        topBar = { TopBar(navController) })
    {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bg_paper),
                contentDescription = "background Image",
                contentScale = ContentScale.FillBounds
            )
            LazyColumn(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .fillMaxSize()
            ) {
                items(viewModel.transactionListState.value) { transactionItem ->
                    TransactionItem(viewModel, transactionItem)
                    Divider(
                        modifier = Modifier.padding(vertical = 4.dp),
                        thickness = 2.dp
                    )
                }
            }
        }

    }


}

@Composable
fun TopBar(navController: NavController) {
    Box(modifier = Modifier.wrapContentHeight()
    ) {
        Image(modifier = Modifier.height(40.dp),
            painter = painterResource(id = R.drawable.bg_paper),
            contentDescription = "background Image",
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth()
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { navController.navigateUp() },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back arrow"
            )
            Text(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .fillMaxWidth(),
                text = "Transactions",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
                fontSize = 30.sp
            )
        }
    }

}


@Composable
fun TransactionItem(viewModel: TransactionScreenViewModel, transactionItem: TransactionItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Column(
            modifier = Modifier
                .weight(0.7f),
            horizontalAlignment = Alignment.Start
        )
        {
            Text(
                text = transactionItem.description,
                style = MaterialTheme.typography.h2
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = transactionItem.dateTime,
                style = MaterialTheme.typography.h1
            )
        }
        Column(
            modifier = Modifier
                .weight(0.3f),
            horizontalAlignment = Alignment.End
        )
        {
            if (transactionItem.transactionType.lowercase() == Constants.TRANSACTION_TYPE_CREDIT) {
                Text(
                    text = "+ ₹ ${transactionItem.spentAmount}",
                    style = MaterialTheme.typography.h1,
                    fontSize = 20.sp

                )
            } else {
                Text(
                    text = "- ₹ ${transactionItem.spentAmount}",
                    style = MaterialTheme.typography.h1,
                    fontSize = 20.sp
                )
            }

        }

    }
}