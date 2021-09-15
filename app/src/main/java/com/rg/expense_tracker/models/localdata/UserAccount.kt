package com.rg.expense_tracker.models.localdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserAccount")
data class UserAccount(
    val accountName: String,
    @PrimaryKey(autoGenerate = true) val id: Int? =null,
    val accountBalance: String,
    val spendingList: List<SpentItems>? = emptyList(),
    val currencyType: String
) {
}