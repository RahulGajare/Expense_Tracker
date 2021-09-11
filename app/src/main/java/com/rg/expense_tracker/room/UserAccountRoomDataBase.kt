package com.rg.expense_tracker.room

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.rg.expense_tracker.models.localdata.UserAccount


@Database(entities = [UserAccount::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class UserAccountRoomDataBase :  RoomDatabase(){

    abstract fun userAccountDao(): UserAccountDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UserAccountRoomDataBase? = null

        fun getDatabase(context: Context): UserAccountRoomDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserAccountRoomDataBase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}