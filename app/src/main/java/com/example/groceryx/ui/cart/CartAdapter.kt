package com.example.groceryx.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryx.R
import com.example.groceryx.data.local.CartItem

class CartAdapter(
    private val onIncrease: (CartItem) -> Unit,
    private val onDecrease: (CartItem) -> Unit,
    private val onRemove: (CartItem) -> Unit
) : ListAdapter<CartItem, CartAdapter.CartVH>(DiffCallback()) {

    inner class CartVH(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView      = view.findViewById(R.id.ivCartProduct)
        val name: TextView        = view.findViewById(R.id.tvCartName)
        val weight: TextView      = view.findViewById(R.id.tvCartWeight)
        val price: TextView       = view.findViewById(R.id.tvCartPrice)
        val tvQty: TextView       = view.findViewById(R.id.tvCartQty)
        val btnPlus: ImageButton  = view.findViewById(R.id.btnCartPlus)
        val btnMinus: ImageButton = view.findViewById(R.id.btnCartMinus)
        val btnDelete: ImageButton= view.findViewById(R.id.btnCartDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartVH(view)
    }

    override fun onBindViewHolder(holder: CartVH, position: Int) {
        val item = getItem(position)
        holder.image.setImageResource(item.imageRes)
        holder.name.text = item.name
        holder.weight.text = item.weight
        holder.price.text = "₹${item.price * item.quantity}"
        holder.tvQty.text = item.quantity.toString()

        holder.btnPlus.setOnClickListener  { onIncrease(item) }
        holder.btnMinus.setOnClickListener { onDecrease(item) }
        holder.btnDelete.setOnClickListener { onRemove(item) }
    }

    class DiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem) =
            oldItem.productId == newItem.productId
        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem) =
            oldItem == newItem
    }
}
