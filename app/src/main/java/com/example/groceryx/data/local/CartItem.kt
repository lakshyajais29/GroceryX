package com.example.groceryx.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey val productId: Int,
    val name: String,
    val price: Int,
    val imageRes: Int,
    val weight: String,
    var quantity: Int
)
