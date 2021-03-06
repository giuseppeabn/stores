package com.example.stores.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "StoreEntity")
data class StoreEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String,
    var phone: String,
    var website: String = "",
    var photoUrl: String,
    var isFavorite: Boolean = false
)
