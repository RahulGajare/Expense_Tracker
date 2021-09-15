package com.rg.expense_tracker.ui

import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rg.expense_tracker.R
import com.rg.expense_tracker.constants.Constants
import com.rg.expense_tracker.ui.splashscreen.SplashScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel: SplashScreenViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)


        coroutineScope.launch {
            val ifExist = viewModel.checkAtleastOneAccountExist()
            if (ifExist) {
                navController.popBackStack()
                navController.navigate(Constants.HOME_SCREEN)

            } else {
                navController.popBackStack()
                navController.navigate(Constants.ADD_ACCOUNT_SCREEN)
            }
        }
    }


    // Image
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.expense_tracker_logo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}