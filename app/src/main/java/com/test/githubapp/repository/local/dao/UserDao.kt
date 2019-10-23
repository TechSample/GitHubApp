package com.test.githubapp.repository.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.githubapp.model.UserEntity


@Dao
interface UserDao {
    @get:Query("SELECT * FROM UserEntity")
    val all: LiveData<List<UserEntity>>

    @Insert
    fun insert(vararg user: UserEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userList: List<UserEntity>)

    @Query("DELETE FROM UserEntity")
    fun deleteAllUsers()

}