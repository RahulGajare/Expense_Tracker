package com.rg.expense_tracker.ui.homeScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopSection()
        BottomSection()

    }
    AccountBalanceCard()


}
@Composable
fun TopSection() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.3f)
        .background(Color.Green))
    {
        Column(modifier = Modifier.wrapContentHeight()) {
            TopNavBar()
            AccountBalance()
        }

    }
}

@Composable
fun BottomSection() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan))
    {

    }
}

@Composable
fun TopNavBar() {
    Box(modifier = Modifier.fillMaxWidth())
    {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back",
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 16.dp, top = 16.dp)
            )
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu",
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp, top = 16.dp))
        }
    }
}

@Composable
fun AccountBalance() {
    Column(modifier = Modifier
        .wrapContentHeight()
        .fillMaxHeight()
        .padding(top = 20.dp, start = 16.dp, end = 16.dp)) {
        Text(text = "Your Wallet",
       fontSize = 20.sp)
        Text(text = "$ 2548",
            fontSize = 40.sp)
    }
}

@Composable
fun AccountBalanceCard() {

    Box(contentAlignment = Alignment.Center ,modifier = Modifier.offset(y = 150.dp))
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
            CircularProgressBar(percentage = 0.5f, number = 100 )
        }
    }


    
}

@Composable
fun CircularProgressBar(
    percentage : Float ,number : Int,
                        radius: Dp = 50.dp,
                        color : Color = Color.Green,
                        strokeWidth : Dp =8.dp,
                        animDuration : Int = 1000,
                        animDelay : Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val curPercentage = animateFloatAsState(targetValue = if(animationPlayed) percentage else 0f,
        animationSpec = tween(durationMillis = animDuration, delayMillis = animDelay))
    
    LaunchedEffect(key1 = true)
    {
        animationPlayed = true
    }
    
    Box(contentAlignment = Alignment.Center,
    modifier = Modifier.size(radius * 2f))
    {
            Canvas(modifier = Modifier.size(radius *2f))
            {
                drawArc(color = Color.Green,
                startAngle = -90f,
                sweepAngle = (360 * curPercentage.value),
                    useCenter = false,
                    style = Stroke(width = strokeWidth.toPx(),cap = StrokeCap.Round)
                )

            }
        
        Text(
            text = (curPercentage.value * number).toInt().toString(),
            color = Color.Black

        )
    }


}

@Preview
@Composable
fun DefaultPreview()
{
    HomeScreen()
}