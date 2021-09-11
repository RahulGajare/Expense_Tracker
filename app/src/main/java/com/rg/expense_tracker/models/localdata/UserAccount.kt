package com.rg.expense_tracker.models.localdata

data class UserAccount (val userName : String,
val mainBalance : Int,
val spendingList : List<SpentItems>,
val currencyType : String) {
}