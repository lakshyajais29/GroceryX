package com.example.groceryx.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryx.R
import com.example.groceryx.data.local.CartItem
import com.example.groceryx.ui.checkout.CheckoutActivity
import com.example.groceryx.utils.Constants

class CartActivity : AppCompatActivity() {

    private val cartViewModel: CartViewModel by viewModels()

    private lateinit var rvCart: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var emptyState: LinearLayout
    private lateinit var cartContent: LinearLayout
    private lateinit var tvItemTotal: TextView
    private lateinit var tvDelivery: TextView
    private lateinit var tvToPay: TextView
    private lateinit var tvDeliveryNote: TextView
    private lateinit var btnCheckout: View
    private lateinit var btnContinueShopping: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Back button in custom toolbar
        findViewById<View>(R.id.btnCartBack).setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        initViews()
        setupRecyclerView()
        observeCart()
    }

    private fun initViews() {
        rvCart              = findViewById(R.id.rvCart)
        emptyState          = findViewById(R.id.emptyState)
        cartContent         = findViewById(R.id.cartContent)
        tvItemTotal         = findViewById(R.id.tvItemTotal)
        tvDelivery          = findViewById(R.id.tvDelivery)
        tvToPay             = findViewById(R.id.tvToPay)
        tvDeliveryNote      = findViewById(R.id.tvDeliveryNote)
        btnCheckout         = findViewById<View>(R.id.btnCheckout)
        btnContinueShopping = findViewById<View>(R.id.btnContinueShopping)

        btnCheckout.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }
        btnContinueShopping.setOnClickListener { finish() }
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(
            onIncrease = { cartViewModel.increaseQuantity(it) },
            onDecrease = { cartViewModel.decreaseQuantity(it) },
            onRemove   = { cartViewModel.removeItem(it) }
        )
        rvCart.layoutManager = LinearLayoutManager(this)
        rvCart.adapter = cartAdapter
    }

    private fun observeCart() {
        cartViewModel.cartItems.observe(this) { items ->
            if (items.isEmpty()) {
                emptyState.visibility  = View.VISIBLE
                cartContent.visibility = View.GONE
            } else {
                emptyState.visibility  = View.GONE
                cartContent.visibility = View.VISIBLE
                cartAdapter.submitList(items)
                updateBillSummary(items)
            }
        }
    }

    private fun updateBillSummary(items: List<CartItem>) {
        val itemTotal      = cartViewModel.getItemTotal(items)
        val deliveryCharge = cartViewModel.getDeliveryCharge(itemTotal)
        val grandTotal     = itemTotal + deliveryCharge

        tvItemTotal.text = "₹$itemTotal"
        tvToPay.text     = "₹$grandTotal"

        if (deliveryCharge == 0) {
            tvDelivery.text     = "FREE"
            tvDeliveryNote.text = "🎉 You saved ₹${Constants.DELIVERY_CHARGE} on delivery!"
            tvDeliveryNote.visibility = View.VISIBLE
        } else {
            tvDelivery.text     = "₹$deliveryCharge"
            val remaining = Constants.FREE_DELIVERY_THRESHOLD - itemTotal
            tvDeliveryNote.text = "Add ₹$remaining more for FREE delivery"
            tvDeliveryNote.visibility = View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
