package com.zako.webetu.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getAppDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("webetu.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
