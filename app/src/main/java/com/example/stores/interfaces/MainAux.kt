package com.example.stores.interfaces

import com.example.stores.model.StoreEntity

interface MainAux {
    fun hideFab(isVisible:Boolean = false)
    fun addStore(storeEntity: StoreEntity)
    fun updateStore(storeEntity: StoreEntity)
}