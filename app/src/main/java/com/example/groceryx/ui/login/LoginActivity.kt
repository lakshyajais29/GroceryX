package com.example.groceryx.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.groceryx.R
import com.example.groceryx.ui.home.HomeActivity
import com.example.groceryx.utils.Constants
import com.example.groceryx.utils.SharedPrefHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var etMobile: EditText
    private lateinit var etOtp: EditText
    private lateinit var btnSendOtp: Button
    private lateinit var btnVerifyOtp: Button
    private lateinit var tvResendOtp: TextView
    private lateinit var otpSection: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Auto-login if already logged in
        if (SharedPrefHelper.isLoggedIn(this)) {
            goToHome()
            return
        }

        setContentView(R.layout.activity_login)

        etMobile    = findViewById(R.id.etMobile)
        etOtp       = findViewById(R.id.etOtp)
        btnSendOtp  = findViewById(R.id.btnSendOtp)
        btnVerifyOtp= findViewById(R.id.btnVerifyOtp)
        tvResendOtp = findViewById(R.id.tvResendOtp)
        otpSection  = findViewById(R.id.otpSection)

        btnSendOtp.setOnClickListener { onSendOtp() }
        btnVerifyOtp.setOnClickListener { onVerifyOtp() }
        tvResendOtp.setOnClickListener {
            Toast.makeText(this, "OTP resent: 1234", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSendOtp() {
        val mobile = etMobile.text.toString().trim()
        when {
            mobile.isEmpty() -> showError("Please enter mobile number")
            mobile.length != 10 -> showError("Enter valid 10-digit number")
            else -> {
                otpSection.visibility = View.VISIBLE
                Toast.makeText(this, "OTP sent! Use: 1234", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onVerifyOtp() {
        val otp = etOtp.text.toString().trim()
        val mobile = etMobile.text.toString().trim()
        when {
            otp.isEmpty() -> showError("Please enter OTP")
            otp != Constants.FAKE_OTP -> showError("Invalid OTP. Try 1234")
            else -> {
                SharedPrefHelper.saveMobile(this, mobile)
                goToHome()
            }
        }
    }

    private fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
