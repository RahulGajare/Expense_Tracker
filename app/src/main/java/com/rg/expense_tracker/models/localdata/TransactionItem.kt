package com.rg.expense_tracker.models.localdata

import java.util.*

data class TransactionItem (val description : String,
                            val spentAmount : String,
                            val dateTime : String,
                            val transactionType : String
) {
}