package com.rg.expense_tracker.models.localdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserAccount")
data class UserAccount(
    val userName: String,
    @PrimaryKey val id: Int,
    val mainBalance: Int,
    val spendingList: List<SpentItems>,
    val currencyType: String
) {
}