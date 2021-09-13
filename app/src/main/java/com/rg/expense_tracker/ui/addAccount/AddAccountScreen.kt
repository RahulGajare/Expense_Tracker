package com.rg.expense_tracker.ui.addAccount

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddAccountScreen(navController : NavController ) {

    Surface(modifier = Modifier.fillMaxSize(),color = MaterialTheme.colors.secondary) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        ){
            Text(text = "Enter Amount that you have allocated for this Account")
            EditTextField(modifier = Modifier.padding(bottom = 6.dp).fillMaxWidth(),
                "" , keyBoardType = KeyboardType.Text)
            {

            }

            Text(text = "Enter a name for this fund, ex:- Trip Fund")
            EditTextField(modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth(),
                "" , keyBoardType = KeyboardType.Number)
            {

            }

        }
    }
}

@Composable
fun EditTextField(modifier: Modifier = Modifier, value : String, keyBoardType : KeyboardType, onTextChange : ()-> Unit)
{
   TextField(modifier = modifier
       .border(border = BorderStroke(width = 2.dp, color = Color.Black)),
       value = value,
       onValueChange = {onTextChange()},
       colors = TextFieldDefaults.textFieldColors(
           backgroundColor = MaterialTheme.colors.secondary,
           focusedIndicatorColor = Color.Transparent,
           unfocusedIndicatorColor = Color.Transparent),
       singleLine = true,
       maxLines = 1,
       keyboardOptions = KeyboardOptions(keyboardType = keyBoardType)

   )

}