package com.zako.webetu.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.zako.webetu.auth.user.data.local.database.UserAuthDao
import com.zako.webetu.auth.user.model.UserAuth
import kotlinx.coroutines.Dispatchers

@Database(
    entities = [
        UserAuth::class
    ],
    version = 1,
    exportSchema = true
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userAuthDao(): UserAuthDao
}

fun RoomDatabase.Builder<AppDatabase>.getWebetuInstance(): AppDatabase {
    return this
//        .addMigrations(MIGRATIONS)
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
