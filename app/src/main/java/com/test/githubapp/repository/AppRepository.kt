package com.test.githubapp.repository

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.test.githubapp.GitHubApp
import com.test.githubapp.model.UserEntity
import com.test.githubapp.repository.remote.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppRepository(val context: Context) {

    var executor : Executor ?= null

    init {

        executor = Executors.newSingleThreadExecutor()
    }

    companion object {

        private var appRepository: AppRepository? = null
        var retrofit: Retrofit? = null
        var gson: Gson? = null
        val BASE_URL = "https://api.github.com/"


        fun getInstance(context: Context): AppRepository {

            if (appRepository == null) {

                appRepository = AppRepository(context)
                gson = Gson()
                retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson!!))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }

            return appRepository!!

        }


    }

        private val userlist: MutableLiveData<List<UserEntity>> = MutableLiveData()
        fun getAllUsersResponse(): MutableLiveData<List<UserEntity>> = userlist

         @SuppressLint("CheckResult")
         fun getAllUsersListFromAPI()
        {
            val apiClient =  retrofit!!.create(ApiService::class.java)
            apiClient.getAllGitHubUsers().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy (
                    onNext = {
                        if(it != null)
                        {
                            userlist.postValue(it)
                            executor!!.execute {
                                GitHubApp.database!!.userDao().deleteAllUsers()
                                GitHubApp.database!!.userDao().insertAll(it)
                            }

                        }
                    },
                    onError = {
                        userlist.postValue(null)
                    },
                    onComplete = {
                    }
                )
        }


    fun getUsers() : LiveData<List<UserEntity>>
    {
        return GitHubApp.database!!.userDao().all

    }

}