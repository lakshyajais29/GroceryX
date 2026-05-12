package com.example.groceryx.data.repository


import androidx.lifecycle.LiveData
import com.example.groceryx.data.local.AppDatabase
import com.example.groceryx.data.local.CartItem
import android.content.Context

class CartRepository(context: Context) {

    private val cartDao = AppDatabase.getDatabase(context).cartDao()

    val allCartItems: LiveData<List<CartItem>> = cartDao.getAllItems()
    val totalItemCount: LiveData<Int> = cartDao.getTotalItemCount()

    suspend fun addOrUpdateItem(item: CartItem) {
        val existing = cartDao.getItemById(item.productId)
        if (existing != null) {
            cartDao.insertOrUpdate(existing.copy(quantity = existing.quantity + 1))
        } else {
            cartDao.insertOrUpdate(item)
        }
    }

    suspend fun decreaseQuantity(item: CartItem) {
        if (item.quantity > 1) {
            cartDao.insertOrUpdate(item.copy(quantity = item.quantity - 1))
        } else {
            cartDao.delete(item)
        }
    }

    suspend fun removeItem(item: CartItem) {
        cartDao.delete(item)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }

    suspend fun getItemById(productId: Int): CartItem? {
        return cartDao.getItemById(productId)
    }
}
