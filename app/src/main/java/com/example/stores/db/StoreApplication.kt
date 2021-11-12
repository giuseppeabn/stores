package com.example.stores.db

import android.app.Application
import androidx.room.Room

class StoreApplication: Application() {

    // TODO: leer mas sobre companion object
    companion object {
        lateinit var dataBase: StoreDataBase
    }

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(
            this,
            StoreDataBase::class.java,
            "StoreDatabase")
            .build()
    }
}