package com.example.stores.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stores.interfaces.StoreDao
import com.example.stores.model.StoreEntity

@Database(entities = arrayOf(StoreEntity::class), version = 1)
abstract class StoreDataBase: RoomDatabase() {
    abstract fun storeDao(): StoreDao
}