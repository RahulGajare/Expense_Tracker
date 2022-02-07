package com.rg.expense_tracker.models.localdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserAccount")
data class UserAccount(
    val accountName: String,
    @PrimaryKey(autoGenerate = true) val id: Int? =null,
    val initialAccountBalance: String,
    var remainingAccountBalance: String,
    val spendingList: MutableList<TransactionItem>? = mutableListOf<TransactionItem>(),
    val currencyType: String

) {
}