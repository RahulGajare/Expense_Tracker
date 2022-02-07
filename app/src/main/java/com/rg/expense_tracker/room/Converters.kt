package com.rg.expense_tracker.room

import androidx.room.TypeConverter
import java.util.*
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import com.rg.expense_tracker.models.CountryCurrency
import com.rg.expense_tracker.models.localdata.SpentItem
import java.lang.reflect.Type


class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return if (date == null) {
            null
        } else {
            date.getTime()
        }
    }


    @TypeConverter
    fun fromString(value: String?): List<SpentItem> {
        val listType: Type = object : TypeToken<List<SpentItem>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<SpentItem>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}