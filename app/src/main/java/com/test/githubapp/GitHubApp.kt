package com.test.githubapp

import android.app.Application
import androidx.room.Room
import com.test.githubapp.repository.local.database.AppDatabase


class GitHubApp : Application(){

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(applicationContext, AppDatabase::class.java, "github_app_db").fallbackToDestructiveMigration().build()
    }
}