package com.example.stores.db

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class StoreApplication: Application() {

    // TODO: leer mas sobre companion object
    companion object {
        lateinit var dataBase: StoreDataBase
    }

    override fun onCreate() {
        super.onCreate()

        val MIGRATION_1_TO_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase){
                database.execSQL("ALTER TABLE StoreEntity ADD COLUMN photoUrl TEXT NOT NULL DEFAULT '")
            }
        }


        dataBase = Room.databaseBuilder(
            this,
            StoreDataBase::class.java,
            "StoreDatabase")
            .addMigrations(MIGRATION_1_TO_2)
            .build()
    }
}