package com.test.githubapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.githubapp.model.UserEntity
import com.test.githubapp.repository.AppRepository

class DashboardViewModel(val context: Context) : ViewModel(){

    var appRepository: AppRepository ?= null

    init {
        appRepository = AppRepository.getInstance(context)
    }

    fun getAllUsersList(): LiveData<List<UserEntity>>
    {
        return appRepository!!.getUsers()
    }


    fun getUsersFromAPI(){

        appRepository!!.getAllUsersListFromAPI()
    }


    class Factory(val context: Context): ViewModelProvider.NewInstanceFactory(){

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DashboardViewModel(context) as T
        }
    }
}