package com.rg.expense_tracker.models

data class Country_CurrencyItem(
    val currency: Currency,
    val flag: String,
    val id: Int,
    val isoAlpha2: String,
    val isoAlpha3: String,
    val isoNumeric: Int,
    val name: String
)