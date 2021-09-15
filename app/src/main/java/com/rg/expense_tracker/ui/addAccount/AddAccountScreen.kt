package com.rg.expense_tracker.ui.addAccount

import android.provider.SyncStateContract
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rg.expense_tracker.constants.Constants
import com.rg.expense_tracker.models.Currency

@Composable
fun AddAccountScreen(navController: NavController) {

    val viewModel: AddAccountViewModel = hiltViewModel()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.primary) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = "Enter a name for this fund, ex:- Trip Fund",
                color = MaterialTheme.colors.onSurface
            )
            EditTextField(
                modifier = Modifier
                    .padding(bottom = 6.dp)
                    .fillMaxWidth(),
                value = viewModel.accountNameState.value,
                keyBoardType = KeyboardType.Text,

                )
            {
                viewModel.accountNameState.value = it
            }
            Text(
                text = "Enter Amount that you have allocated for this Account",
                color = MaterialTheme.colors.onSurface,
            )

            EditTextField(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(),
                value = viewModel.accountBalanceState.value.toString(),
                keyBoardType = KeyboardType.Number
            )
            {
                viewModel.accountBalanceState.value = it
            }

            Text(
                text = "Currency",
                color = MaterialTheme.colors.onSurface,
            )

            Text(text = viewModel.currencyState.value,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .border(border = BorderStroke(width = 2.dp, color = Color.Black))
                    .fillMaxWidth()
                    .padding(6.dp)
                    .clickable {

                        navController.navigate(Constants.CURRENCY_SELECT_SCREEN)
                    }

            )

            Button(modifier = Modifier.background(color = MaterialTheme.colors.primaryVariant),
                onClick = { viewModel.addNewAccount()}) {
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
fun EditTextField(
    modifier: Modifier = Modifier,
    value: String,
    keyBoardType: KeyboardType,
    onTextChange: (newText: String) -> Unit
) {
    TextField(
        modifier = modifier
            .border(border = BorderStroke(width = 2.dp, color = Color.Black)),
        value = value,
        onValueChange = { onTextChange(it.toString()) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.primaryVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = keyBoardType)

    )

}