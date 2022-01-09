package com.rg.expense_tracker.ui.homeScreen

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rg.expense_tracker.constants.Constants
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
                viewModel.speakToTextState.value = recognizedText
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
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
            .fillMaxWidth())
        BottomSectionHomeScreen(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp), viewModel)
        ExpenseInputOption(modifier = Modifier.fillMaxWidth().
        padding(top = 20.dp)){
            if(it.equals(Constants.TALK))
            {
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
        }
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

// Debit and Credit buttons
@Composable
fun RadioOptions(modifier: Modifier , selectedValue : String , onSelected : (String)-> Unit)
{
    val radioOptions = listOf("Debit", "Credit")
    Row(modifier = modifier ,
        horizontalArrangement = Arrangement.SpaceEvenly) {
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
                    text = it,
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
        Text(modifier = Modifier.border(
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colors.primary)).
            padding(8.dp).clickable  { onClick(Constants.TALK) },
            text = "talk",
            fontSize = 20.sp
        )
        Text(modifier = Modifier.border(
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colors.primary)).
            padding(8.dp).
            clickable  { onClick(Constants.TYPE) },
            text = "type",
            fontSize = 20.sp
        )
    }
}

@Composable
private fun onMicButtonClick(resultLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>) {


}


//@Composable
//@Preview
//fun HomeScreePreview()
//{
//    HomeScreen()
//}