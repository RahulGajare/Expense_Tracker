package com.rg.expense_tracker.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rg.expense_tracker.R

val fonts = FontFamily(
    Font(R.font.yagi, weight = FontWeight.Light),
    Font(R.font.yagi, weight = FontWeight.Bold),
    Font(R.font.yagi, weight = FontWeight.Medium)

)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
            h3 = TextStyle(
            fontFamily = fonts,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp
)



)

