package com.zako.webetu.auth.user.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zako.webetu.auth.user.model.UserAuth

@Dao
interface UserAuthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAuth(userAuth: UserAuth)

    @Query("SELECT * FROM UserAuth WHERE id = :id")
    suspend fun getUserAuthById(id: String): UserAuth?

    @Delete
    suspend fun deleteUserAuth(userAuth: UserAuth)

    /**
     * this only persist if the app keep supporting one user per device
     * it's just getting the first user in the database
     *
     * @author B.zakaria
     */
    @Query("SELECT * FROM UserAuth LIMIT 1")
    suspend fun getDefaultUserAuth(): UserAuth?
}
