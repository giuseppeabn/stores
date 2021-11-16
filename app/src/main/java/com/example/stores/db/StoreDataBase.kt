package com.example.stores.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stores.interfaces.StoreDao
import com.example.stores.model.StoreEntity

@Database(entities = [StoreEntity::class], version = 2)
abstract class StoreDataBase: RoomDatabase() {
    abstract fun storeDao(): StoreDao
}