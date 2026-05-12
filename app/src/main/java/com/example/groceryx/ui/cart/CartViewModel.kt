package com.example.groceryx.ui.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.groceryx.data.local.CartItem
import com.example.groceryx.data.repository.CartRepository
import com.example.groceryx.utils.Constants
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CartRepository(application)

    val cartItems: LiveData<List<CartItem>> = repository.allCartItems
    val cartItemCount: LiveData<Int> = repository.totalItemCount

    fun addToCart(item: CartItem) = viewModelScope.launch {
        repository.addOrUpdateItem(item)
    }

    fun increaseQuantity(item: CartItem) = viewModelScope.launch {
        repository.addOrUpdateItem(item)
    }

    fun decreaseQuantity(item: CartItem) = viewModelScope.launch {
        repository.decreaseQuantity(item)
    }

    fun removeItem(item: CartItem) = viewModelScope.launch {
        repository.removeItem(item)
    }

    fun clearCart() = viewModelScope.launch {
        repository.clearCart()
    }

    fun getItemTotal(items: List<CartItem>): Int {
        return items.sumOf { it.price * it.quantity }
    }

    fun getDeliveryCharge(itemTotal: Int): Int {
        return if (itemTotal >= Constants.FREE_DELIVERY_THRESHOLD) 0 else Constants.DELIVERY_CHARGE
    }

    fun getGrandTotal(items: List<CartItem>): Int {
        val itemTotal = getItemTotal(items)
        return itemTotal + getDeliveryCharge(itemTotal)
    }
}
