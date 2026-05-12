package com.example.groceryx.ui.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryx.R
import com.example.groceryx.data.local.CartItem
import com.example.groceryx.data.model.Product

class ProductAdapter(
    private val onAddToCart: (CartItem) -> Unit,
    private val onIncrease: (CartItem) -> Unit,
    private val onDecrease: (CartItem) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductVH>() {

    private var productList: MutableList<Product> = mutableListOf()
    // Map of productId -> quantity currently in cart
    private val cartQuantities: HashMap<Int, Int> = HashMap()

    fun submitList(list: List<Product>) {
        productList = list.toMutableList()
        notifyDataSetChanged()
    }

    fun updateCartQuantities(quantities: Map<Int, Int>) {
        cartQuantities.clear()
        cartQuantities.putAll(quantities)
        notifyDataSetChanged()
    }

    inner class ProductVH(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView    = view.findViewById(R.id.ivProduct)
        val name: TextView      = view.findViewById(R.id.tvProductName)
        val weight: TextView    = view.findViewById(R.id.tvProductWeight)
        val price: TextView     = view.findViewById(R.id.tvProductPrice)
        val btnAdd: TextView    = view.findViewById(R.id.btnAdd)
        val stepperLayout: LinearLayout = view.findViewById(R.id.stepperLayout)
        val btnMinus: ImageButton = view.findViewById(R.id.btnMinus)
        val btnPlus: ImageButton  = view.findViewById(R.id.btnPlus)
        val tvQty: TextView       = view.findViewById(R.id.tvQty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductVH(view)
    }

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        val product = productList[position]
        val qty = cartQuantities[product.id] ?: 0

        holder.image.setImageResource(product.imageRes)
        holder.name.text = product.name
        holder.weight.text = product.weight
        holder.price.text = "₹${product.price}"

        if (qty > 0) {
            holder.btnAdd.visibility = View.GONE
            holder.stepperLayout.visibility = View.VISIBLE
            holder.tvQty.text = qty.toString()
        } else {
            holder.btnAdd.visibility = View.VISIBLE
            holder.stepperLayout.visibility = View.GONE
        }

        holder.btnAdd.setOnClickListener {
            val cartItem = CartItem(product.id, product.name, product.price, product.imageRes, product.weight, 1)
            onAddToCart(cartItem)
        }

        holder.btnPlus.setOnClickListener {
            val cartItem = CartItem(product.id, product.name, product.price, product.imageRes, product.weight, qty)
            onIncrease(cartItem)
        }

        holder.btnMinus.setOnClickListener {
            val cartItem = CartItem(product.id, product.name, product.price, product.imageRes, product.weight, qty)
            onDecrease(cartItem)
        }
    }

    override fun getItemCount() = productList.size
}
