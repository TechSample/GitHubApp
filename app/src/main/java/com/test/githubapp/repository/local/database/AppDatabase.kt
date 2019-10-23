package com.test.githubapp.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.githubapp.model.UserEntity
import com.test.githubapp.repository.local.dao.UserDao

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}