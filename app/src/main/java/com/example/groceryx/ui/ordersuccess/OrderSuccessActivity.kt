package com.example.groceryx.ui.ordersuccess


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.groceryx.R
import com.example.groceryx.ui.home.HomeActivity
import com.example.groceryx.utils.Constants

class OrderSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)

        val orderId      = intent.getStringExtra("ORDER_ID") ?: "OX000000"
        val paymentMode  = intent.getStringExtra("PAYMENT_MODE") ?: "Cash on Delivery"
        val grandTotal   = intent.getIntExtra("GRAND_TOTAL", 0)
        val address      = intent.getStringExtra("DELIVERY_ADDRESS") ?: ""

        findViewById<TextView>(R.id.tvOrderId).text = "Order ID: $orderId"
        findViewById<TextView>(R.id.tvPaymentMode).text = "Payment: $paymentMode"
        findViewById<TextView>(R.id.tvEstDelivery).text =
            "Est. Delivery: ${Constants.ESTIMATED_DELIVERY}"
        findViewById<TextView>(R.id.tvAmountPaid).text = "Amount Paid: ₹$grandTotal"
        findViewById<TextView>(R.id.tvDeliveryAddress).text = "Delivering to: $address"

        findViewById<Button>(R.id.btnBackToHome).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
            finish()
        }
    }
}
