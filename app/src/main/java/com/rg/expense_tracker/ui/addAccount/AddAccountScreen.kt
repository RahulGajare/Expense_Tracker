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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rg.expense_tracker.R
import com.rg.expense_tracker.constants.Constants
import com.rg.expense_tracker.models.Currency

@Composable
fun AddAccountScreen(navController: NavController) {

    val viewModel: AddAccountViewModel = hiltViewModel()

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
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
                try {
                    if(it.isEmpty())
                    {
                        viewModel.accountBalanceState.value = it
                    }
                    else if(it.toIntOrNull() != null)
                        {
                            viewModel.accountBalanceState.value = it
                        }
                }
                catch (ex : Exception)
                {
                    // Amount Filled is empty
                }
            }
Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
    AmountChips("100") {
        //it ==0 for negative.
        //it ==1 for positive
       viewModel.checkValidAmount(100, it)
    }
    AmountChips("500")
    {
        viewModel.checkValidAmount(500 ,it)
    }
    AmountChips("1000")
    {
        viewModel.checkValidAmount(1000,it)
    }

}


            Button(modifier = Modifier.background(color = MaterialTheme.colors.primaryVariant),
                onClick = { viewModel.addNewAccount()
                    navController.navigate(Constants.HOME_SCREEN)}) {
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
    value: String = "",
    keyBoardType: KeyboardType,
    onTextChange: (newText: String) -> Unit
) {
    TextField(
        modifier = modifier
            .border(border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.primaryVariant)),
        value = value,
        onValueChange = { onTextChange(it.toString()) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = keyBoardType)

    )

}

@Composable
fun AmountChips(value : String , onAmountAclicked : (math : Int)-> Unit ) {
    Row(verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .border(width = 2.dp, color = Color.Black, RoundedCornerShape(25.dp))
            .clip(
                shape = RoundedCornerShape(25.dp)
            )
            .background(color = MaterialTheme.colors.primary)
            .height(30.dp)
            .wrapContentWidth()

    ) {

        Image(painterResource(R.drawable.subtraction),contentDescription = "Subtract",modifier = Modifier
            .padding(horizontal = 6.dp)
            .size(15.dp)
            . clickable { onAmountAclicked(0) })


        Text(text = value ,color = Color.Black, modifier = Modifier.background(Color.Transparent), fontSize = 20.sp)

        Image(painterResource(R.drawable.plus),contentDescription = "Subtract",modifier = Modifier
            .padding(horizontal = 6.dp)
            .size(15.dp)
            .clickable { onAmountAclicked(1) })

        
    }
    
}