package com.rg.expense_tracker.ui.homeScreen

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.rg.expense_tracker.R
import com.rg.expense_tracker.constants.Constants
import kotlinx.coroutines.launch
import java.util.*


@Composable
fun HomeScreenNew (navController: NavController)
{
    val viewModel: HomeScreenViewModel= hiltViewModel()
    val context = LocalContext.current
    var resultLauncher   =  rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data

            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            // Ensure result array is not null or empty to avoid errors.
            if (!result.isNullOrEmpty()) {
                // Recognized text is in the first position.
                val recognizedText = result[0]
               val amount = recognizedText.filter { it.isDigit() }
                val description = recognizedText.replace(amount,"")
                viewModel.transactionDescriptionState.value = description
                if (amount.isDigitsOnly())
                viewModel.transactionAmountState.value = amount
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.bg_paper),
            contentDescription = "background Image",
        contentScale = ContentScale.FillBounds)
        Scaffold(backgroundColor = Color.Transparent)
        {
            Box(modifier = Modifier.fillMaxSize().
            background(color = Color.Transparent)) {
                Column(modifier = Modifier
                    .statusBarsPadding()
                    .navigationBarsWithImePadding()
                    .fillMaxSize()

                    .padding(12.dp))
                {

                    TopSection(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp) , viewModel = viewModel
                    )
                    DetailCard(modifier = Modifier
                        .border(
                            border = BorderStroke(
                                width = 2.dp,
                                color = MaterialTheme.colors.primary
                            )
                        )
                        .fillMaxWidth(), viewModel)
                    BottomSectionHomeScreen(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp), viewModel)
                    ExpenseInputOption(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)){
                        if(it==Constants.TALK)
                        {
                            onMicButtonClick(resultLauncher,context)
                        }
                        viewModel.transactionCardVisibility.value = true;
                    }

                }

                if(viewModel.transactionCardVisibility.value)
                {
                    TransactionTextInputCard(viewModel)
                    {
                        viewModel.transactionDescriptionState.value = it
                    }
                }


            }

        }


    }




}

@Composable
fun TopSection(modifier: Modifier , viewModel: HomeScreenViewModel)
{
    Column(modifier = modifier)
    {
        Text(
            text = viewModel.walletNameState.value,
            style = MaterialTheme.typography.h1,
            fontSize = 40.sp
        )

        Text(
            text = "₹ ${viewModel.initialAccountBalanceState.value}",
            style = MaterialTheme.typography.h1,
            fontSize = 35.sp
        )
    }
}

@Composable
fun DetailCard(modifier: Modifier , viewModel: HomeScreenViewModel)
{
    Box(contentAlignment = Alignment.Center,
        modifier = modifier)
    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            Row() {
                Text(
                    text = "Remaining Amount ",
                    style = MaterialTheme.typography.h1,
                    fontSize = 20.sp
                )

                Text(
                    text = "₹ ${viewModel.remainingAccountBalanceState.value}",
                    style = MaterialTheme.typography.h1,
                    fontSize = 20.sp
                )
            }

            Row() {
                Text(
                    text = "Spent Amount ",
                    style = MaterialTheme.typography.h1,
                    fontSize = 20.sp
                )

                Text(
                    text ="₹ ${viewModel.spentAccountBalanceState.value}" ,
                    style = MaterialTheme.typography.h1,
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

// Debit and Credit buttons
@Composable
fun RadioOptions(modifier: Modifier , selectedValue : String , onSelected : (String)-> Unit)
{
    val radioOptions = listOf("Debit", "Credit")
    Row(modifier = modifier ,
        horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically) {
        radioOptions.forEach{it ->
            Row(modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colors.primary
                    )
                )
                .padding(8.dp)
                .selectable(selected = (it == selectedValue),
                    onClick = { onSelected(it) })) {

                RadioButton(selected = (it == selectedValue),
                    colors = RadioButtonDefaults.colors(selectedColor =MaterialTheme.colors.primary,
                        ),
                    onClick = { onSelected(it) })
                Text(
                    modifier =Modifier.align(alignment = Alignment.CenterVertically),
                    text = it,
                    style = MaterialTheme.typography.h1,
                    fontSize = 20.sp
                )
            }

        }

    }

}

//Type/ Talk button
@Composable
fun ExpenseInputOption(modifier: Modifier , onClick : (String) -> Unit)
{
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(modifier = Modifier
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colors.primary
                )
            )
            .padding(8.dp)
            .clickable { onClick(Constants.TALK) },
            text = "talk",
            style = MaterialTheme.typography.h1,
            fontSize = 20.sp
        )
        Text(modifier = Modifier
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colors.primary
                )
            )
            .padding(8.dp)
            .clickable { onClick(Constants.TYPE) },
            text = "type",
            style = MaterialTheme.typography.h1,
            fontSize = 20.sp
        )
    }
}

private fun onMicButtonClick(
    resultLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    context: Context
) {
    // Get the Intent action
    val sttIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    // Language model defines the purpose, there are special models for other use cases, like search.
    sttIntent.putExtra(
        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
    )
    // Adding an extra language, you can use any language from the Locale class.
    sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
    // Text that shows up on the Speech input prompt.
    sttIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now!")
    try {
        // Start the intent for a result, and pass in our request code.

        resultLauncher?.launch(sttIntent)
    } catch (e: ActivityNotFoundException) {
        // Handling error when the service is not available.
        e.printStackTrace()
        Toast.makeText(context, "Your device does not support STT.", Toast.LENGTH_LONG).show()
    }

}

@Composable
fun TransactionTextInputCard(viewModel: HomeScreenViewModel , onTextChange: (newText: String) -> Unit)
{
    ProvideWindowInsets(windowInsetsAnimationsEnabled = true)
        {
            Box(
                Modifier
                    .fillMaxSize()
                    .navigationBarsWithImePadding())
            {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.BottomStart),
                    contentAlignment = Alignment.BottomStart
                )
                {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = MaterialTheme.colors.surface,
                    )
                    {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Description",
                                style = MaterialTheme.typography.h1)
                            EditTextField(value = viewModel.transactionDescriptionState.value,
                                keyBoardType = KeyboardType.Text,
                                maxLines = 1,
                                singleLine = true)
                            {
                                viewModel.transactionDescriptionState.value = it
                            }
                            Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)

                            Text(text = "Amount" ,
                                style = MaterialTheme.typography.h1,
                                modifier = Modifier.padding(top = 6.dp))
                            EditTextField(value = viewModel.transactionAmountState.value,
                                keyBoardType = KeyboardType.Number,
                                maxLines = 1,
                                singleLine = true)
                            {
                                try {
                                    // this condition for enabling the deletion of last letter
                                    if(it.isEmpty())
                                    {
                                        viewModel.transactionAmountState.value = it
                                    }
                                    else if(it.toIntOrNull() != null)
                                    {
                                        viewModel.transactionAmountState.value = it
                                    }
                                }
                                catch (ex : Exception)
                                {
                                    // Amount Filled is empty
                                }
                            }


                            Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)
                            val coroutineScope = rememberCoroutineScope()
                            Button(onClick = { coroutineScope.launch{viewModel.createTransaction()}  },
                                modifier = Modifier.padding(top = 6.dp)
                                    .border(
                                        border = BorderStroke(
                                            width = 2.dp,
                                            color = MaterialTheme.colors.primary
                                        )
                                    ),
                                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                            ) {
                                Text(text = "Confirm",
                                    style = MaterialTheme.typography.h1)
                            }
                        }
                    }

                }
            }
        }
}

@Composable
fun EditTextField(value :String,
                  modifier: Modifier = Modifier,
                  keyBoardType: KeyboardType,
                  singleLine : Boolean = false,
                  maxLines: Int,
                  onValueChange : (String)-> Unit)

{
    TextField(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.Transparent),
        value = value,
        onValueChange = { onValueChange(it)},
        singleLine = singleLine,
        maxLines = maxLines,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent),
        keyboardOptions = KeyboardOptions(keyboardType = keyBoardType))
}





//@Composable
//@Preview
//fun HomeScreePreview()
//{
//    HomeScreen()
//}