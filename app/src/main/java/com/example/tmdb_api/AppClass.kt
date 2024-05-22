package com.example.tmdb_api

import android.annotation.SuppressLint
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClass: Application() {

    companion object{
        //@SuppressLint("StaticFieldLeak")
        //lateinit var prefRepository: PrefRepository
    }

    override fun onCreate() {
        super.onCreate()
        //prefRepository = PrefRepository(applicationContext)
    }
}