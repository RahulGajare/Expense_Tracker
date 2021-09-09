package com.rg.expense_tracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExpenseTrackerApplication : Application()  {

    override fun onCreate() {
        super.onCreate()

    }
}