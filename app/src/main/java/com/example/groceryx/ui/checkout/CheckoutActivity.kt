package com.example.groceryx.ui.checkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.groceryx.R
import com.example.groceryx.ui.cart.CartViewModel
import com.example.groceryx.ui.ordersuccess.OrderSuccessActivity
import com.example.groceryx.utils.SharedPrefHelper

class CheckoutActivity : AppCompatActivity() {

    private val cartViewModel: CartViewModel by viewModels()

    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etAddress: EditText
    private lateinit var etCity: EditText
    private lateinit var etPincode: EditText
    private lateinit var rgPayment: RadioGroup
    private lateinit var rbCod: RadioButton
    private lateinit var rbOnline: RadioButton
    private lateinit var tvOrderGrandTotal: TextView
    private lateinit var btnPlaceOrder: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // Back button in custom toolbar
        findViewById<View>(R.id.btnCheckoutBack).setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        initViews()
        observeCart()
    }

    private fun initViews() {
        etName          = findViewById(R.id.etName)
        etPhone         = findViewById(R.id.etPhone)
        etAddress       = findViewById(R.id.etAddress)
        etCity          = findViewById(R.id.etCity)
        etPincode       = findViewById(R.id.etPincode)
        rgPayment       = findViewById(R.id.rgPayment)
        rbCod           = findViewById(R.id.rbCod)
        rbOnline        = findViewById(R.id.rbOnline)

        tvOrderGrandTotal   = findViewById(R.id.tvOrderGrandTotal)
        btnPlaceOrder   = findViewById<View>(R.id.btnPlaceOrder)

        // Pre-fill phone from SharedPrefs
        etPhone.setText(SharedPrefHelper.getMobile(this))

        rbOnline.setOnClickListener {
            Toast.makeText(this, "Online Payment coming soon!", Toast.LENGTH_SHORT).show()
            rbCod.isChecked = true
        }

        btnPlaceOrder.setOnClickListener { validateAndPlaceOrder() }
    }

    private fun observeCart() {
        cartViewModel.cartItems.observe(this) { items ->
            val itemTotal      = cartViewModel.getItemTotal(items)
            val deliveryCharge = cartViewModel.getDeliveryCharge(itemTotal)
            val grandTotal     = itemTotal + deliveryCharge


            tvOrderGrandTotal.text = "₹$grandTotal"
        }
    }

    private fun validateAndPlaceOrder() {
        val name    = etName.text.toString().trim()
        val phone   = etPhone.text.toString().trim()
        val address = etAddress.text.toString().trim()
        val city    = etCity.text.toString().trim()
        val pincode = etPincode.text.toString().trim()

        when {
            name.isEmpty()       -> showError("Enter your name")
            phone.length != 10   -> showError("Enter valid 10-digit phone number")
            address.isEmpty()    -> showError("Enter delivery address")
            city.isEmpty()       -> showError("Enter city")
            pincode.length != 6  -> showError("Enter valid 6-digit pincode")
            else -> {
                val paymentMode = if (rbCod.isChecked) "Cash on Delivery" else "Online"
                val orderId = "OX" + System.currentTimeMillis().toString().takeLast(6)

                cartViewModel.cartItems.value?.let { items ->
                    val grandTotal = cartViewModel.getGrandTotal(items)
                    cartViewModel.clearCart()

                    val intent = Intent(this, OrderSuccessActivity::class.java).apply {
                        putExtra("ORDER_ID", orderId)
                        putExtra("PAYMENT_MODE", paymentMode)
                        putExtra("GRAND_TOTAL", grandTotal)
                        putExtra("DELIVERY_ADDRESS", "$address, $city - $pincode")
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
