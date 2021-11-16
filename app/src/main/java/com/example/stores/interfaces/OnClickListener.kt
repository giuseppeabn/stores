package com.example.stores.interfaces

import com.example.stores.model.StoreEntity

interface OnClickListener {
    fun onClick(storeId: Long)
    fun onFavoriteStore(storeEntity: StoreEntity)
    fun onDeleteStore(storeEntity: StoreEntity)
}