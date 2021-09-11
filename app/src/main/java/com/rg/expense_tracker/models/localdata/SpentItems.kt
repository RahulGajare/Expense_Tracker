package com.rg.expense_tracker.models.localdata

import java.util.*

data class SpentItems (val description : String,
val spentAmount : Int,
val dateTime : Date
) {
}