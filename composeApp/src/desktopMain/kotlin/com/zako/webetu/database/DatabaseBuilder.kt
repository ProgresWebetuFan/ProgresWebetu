package com.zako.webetu.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

fun getAppDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "webetu.db")
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    )
}
