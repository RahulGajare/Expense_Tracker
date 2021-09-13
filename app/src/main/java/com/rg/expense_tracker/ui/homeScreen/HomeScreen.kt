package com.rg.expense_tracker.ui.homeScreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rg.expense_tracker.constants.Constants

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopSection(navController =navController)
        BottomSection()

    }
    AccountBalanceCard()


}

@Composable
fun TopSection(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(MaterialTheme.colors.secondary)
    )
    {
        Column(modifier = Modifier.wrapContentHeight()) {
            TopNavBar()
            AccountBalance(mainBalance = 200 , currency = "$")
            {
                navController.navigate(Constants.CURRENCY_SELECT_SCREEN)
            }
        }

    }
}

@Composable
fun BottomSection() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {

    }
}

@Composable
fun TopNavBar() {
    Box(modifier = Modifier.fillMaxWidth())
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
//            Icon(
//                imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back",
//                modifier = Modifier
//                    .size(40.dp)
//                    .padding(start = 16.dp, top = 16.dp)
//            )
            Icon(
                imageVector = Icons.Default.Menu, contentDescription = "Menu",
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp, top = 16.dp)
            )
        }
    }
}

@Composable
fun AccountBalance(mainBalance : Int , currency :String , onCurrencyClick : () -> Unit) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxHeight()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "Your Wallet",
            fontSize = 20.sp
        )
        Row() {
            Text(
                text = "$currency $mainBalance",
                fontSize = 40.sp
            )
            val result = remember {
                mutableStateOf("INR")
            }
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent())
            {
                result.value = it.toString()
            }
            Text(modifier = Modifier.clickable(onClick = {launcher.launch()}),
                text = "INR",
                fontSize = 40.sp
            )
        }

    }
}

@Composable
fun AccountBalanceCard() {

    Box(contentAlignment = Alignment.Center, modifier = Modifier.offset(y = 150.dp))
    {
        Card(
            backgroundColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            elevation = 20.dp
        )
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly) {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment =  Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CircularProgressBar(percentage = 1f, number = 100)
                    Column() {
                        Text(text = "Remaining Balance",
                            modifier = Modifier.offset(x = 5.dp))
                        Text(text = "60 % of Main Balance spent this month",
                            modifier = Modifier.offset(x = 5.dp))
                    }

                }
                Divider(thickness = 2.dp,
                modifier = Modifier.padding(6.dp))

                Icon(
                    imageVector = Icons.Default.AddCircle, contentDescription = "Add Expense",
                    modifier = Modifier
                        .size(40.dp)
                )
                Text(text = "Add expense")


            }

        }
    }


}

@Composable
fun CircularProgressBar(
    percentage: Float, number: Int,
    radius: Dp = 20.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 1.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(durationMillis = animDuration, delayMillis = animDelay)
    )

    LaunchedEffect(key1 = true)
    {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    )
    {
        Canvas(modifier = Modifier.size(radius * 2f))
        {
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = (360 * curPercentage.value),
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

        }

        Text(
            text = (curPercentage.value * number).toInt().toString() + "%",
            color = Color.Black

        )
    }


}

@Preview
@Composable
fun DefaultPreview() {

}