package com.rg.expense_tracker.ui.addAccount

import android.provider.SyncStateContract
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.rg.expense_tracker.R
import com.rg.expense_tracker.constants.Constants
import com.rg.expense_tracker.models.Currency
import com.rg.expense_tracker.ui.homeScreen.EditTextField
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun AddAccountScreen(navController: NavController) {

    val viewModel: AddAccountViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .navigationBarsWithImePadding()
                .statusBarsPadding(),
        ) {
            Text(
                fontSize = 26.sp,
                text = "Enter a name for this fund, ex:- Trip Fund",
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onSurface
            )
            EditTextField(
                value = viewModel.accountNameState.value,
                keyBoardType = KeyboardType.Text,
                maxLines = 1,
                singleLine = true
            )
            {
                viewModel.accountNameState.value = it
            }

            Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)

            Text(
                modifier = Modifier.padding(top = 6.dp),
                fontSize = 26.sp,
                text = "Enter Amount that you have allocated for this Account",
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onSurface,
            )

            EditTextField(
                value = viewModel.accountBalanceState.value,
                keyBoardType = KeyboardType.Number,
                maxLines = 1,
                singleLine = true
            )
            {
                try {
                    if (it.isEmpty()) {
                        viewModel.accountBalanceState.value = it
                    } else if (it.toIntOrNull() != null) {
                        viewModel.accountBalanceState.value = it
                    }
                } catch (ex: Exception) {
                    // Amount Filled is empty
                }
            }

            Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)
            Row(
                horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                AmountChips("100") {
                    //it ==0 for negative.
                    //it ==1 for positive
                    viewModel.checkValidAmount(100, it)
                }
                AmountChips("500")
                {
                    viewModel.checkValidAmount(500, it)
                }
                AmountChips("1000")
                {
                    viewModel.checkValidAmount(1000, it)
                }

            }


            Button(modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 6.dp)
                .border(
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colors.primary
                    )
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                onClick = {
                    coroutineScope.launch {
                        val isSuccess = viewModel.addNewAccount()
                        if (isSuccess)
                        navController.navigate(Constants.HOME_SCREEN)
                    }

                }) {
                Text(text = "Proceed")
            }
        }
    }

    val currencyScreenResult = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>("selected_Currency")
        ?.observeAsState()

    currencyScreenResult?.value.let {
        if (it != null) {
            viewModel.currencyState.value = it
        }
    }
}


@Composable
fun AmountChips(value: String, onAmountAclicked: (math: Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .border(width = 2.dp, color = Color.Black, RoundedCornerShape(25.dp))
            .clip(
                shape = RoundedCornerShape(25.dp)
            )
            .background(color = MaterialTheme.colors.secondary)
            .height(30.dp)
            .wrapContentWidth()

    ) {

        Image(painterResource(R.drawable.subtraction),
            contentDescription = "Subtract",
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 6.dp)
                .size(15.dp)
                .clickable { onAmountAclicked(0) })


        Text(
            text = value,
            color = Color.Black,
            modifier = Modifier.background(Color.Transparent),
            fontSize = 20.sp
        )

        Image(painterResource(R.drawable.plus), contentDescription = "Add", modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 6.dp)
            .size(15.dp)
            .clickable { onAmountAclicked(1) })


    }

}