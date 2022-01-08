package com.rg.expense_tracker.ui.homeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.material.color.MaterialColors
import com.rg.expense_tracker.ui.Utility.CheckboxText

@Composable
fun HomeScreenNew (navController: NavController)
{
    val viewModel: HomeScreenViewModel= hiltViewModel()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(12.dp))
    {
        TopSection(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp) , viewModel = viewModel
        )
        DetailCard(modifier =  Modifier.border(border = BorderStroke(width = 2.dp,
            color = MaterialTheme.colors.primary)))
        BottomSectionHomeScreen(modifier = Modifier.fillMaxWidth() , viewModel)
    }
}

@Composable
fun TopSection(modifier: Modifier , viewModel: HomeScreenViewModel)
{
    Column(modifier = modifier)
    {
        Text(
            text = "Main Balance",
            fontSize = 40.sp
        )

        Text(
            text = "₹ ${viewModel.initialAccountBalanceState.value}",
            fontSize = 35.sp
        )
    }
}

@Composable
fun DetailCard(modifier: Modifier)
{
    Box(contentAlignment = Alignment.Center,
        modifier = modifier)
    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            Row() {
                Text(
                    text = "Initial Amount ",
                    fontSize = 20.sp
                )

                Text(
                    text = "₹ 1000",
                    fontSize = 20.sp
                )
            }

            Row() {
                Text(
                    text = "Spent Amount ",
                    fontSize = 20.sp
                )

                Text(
                    text = "₹ 500",
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun BottomSectionHomeScreen(modifier: Modifier , viewModel: HomeScreenViewModel)
{
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
           RadioOptions(modifier = Modifier.fillMaxWidth(),
               selectedValue = viewModel.radioOptionsSelectedState.value,
               onSelected = {
                   viewModel.radioOptionsSelectedState.value = it
               })
        }
    }
}

@Composable
fun RadioOptions(modifier: Modifier , selectedValue : String , onSelected : (String)-> Unit)
{
    val radioOptions = listOf("Debit", "Credit")
    Row(modifier = modifier ,
        horizontalArrangement = Arrangement.SpaceEvenly) {
        radioOptions.forEach{it ->
            Row(modifier = Modifier
                .selectable(selected = (it == selectedValue),
                    onClick = { onSelected(it) })) {

                RadioButton(selected = (it == selectedValue),
                    onClick = { onSelected(it) })
                Text(
                    text = it,
                    fontSize = 20.sp
                )
            }

        }

    }

}


//@Composable
//@Preview
//fun HomeScreePreview()
//{
//    HomeScreen()
//}