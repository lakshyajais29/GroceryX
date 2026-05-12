package com.example.groceryx.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.groceryx.R
import com.example.groceryx.ui.cart.CartActivity
import com.example.groceryx.ui.cart.CartViewModel
import com.example.groceryx.utils.Constants

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var rvProducts: RecyclerView
    private lateinit var rvCategories: RecyclerView
    private lateinit var viewPagerBanner: ViewPager2
    private lateinit var searchView: SearchView
    private lateinit var tvCartCount: TextView
    private lateinit var btnCart: FrameLayout
    private lateinit var tvSectionTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        setupBanner()
        setupCategories()
        setupProducts()
        observeViewModel()
    }

    private fun initViews() {
        rvProducts      = findViewById(R.id.rvProducts)
        rvCategories    = findViewById(R.id.rvCategories)
        viewPagerBanner = findViewById(R.id.viewPagerBanner)
        searchView      = findViewById(R.id.searchView)
        tvCartCount     = findViewById(R.id.tvCartCount)
        btnCart         = findViewById(R.id.btnCart)
        tvSectionTitle  = findViewById(R.id.tvSectionTitle)

        btnCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                homeViewModel.search(newText ?: "")
                return true
            }
        })
    }

    private fun setupBanner() {
        val bannerAdapter = BannerAdapter(Constants.BANNERS)
        viewPagerBanner.adapter = bannerAdapter
    }

    private fun setupCategories() {
        categoryAdapter = CategoryAdapter(Constants.CATEGORIES) { category ->
            homeViewModel.filterByCategory(category.name)
            tvSectionTitle.text = category.name
        }
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoryAdapter
    }

    private fun setupProducts() {
        productAdapter = ProductAdapter(
            onAddToCart = { cartViewModel.addToCart(it) },
            onIncrease  = { cartViewModel.increaseQuantity(it) },
            onDecrease  = { cartViewModel.decreaseQuantity(it) }
        )
        rvProducts.layoutManager = GridLayoutManager(this, 2)
        rvProducts.adapter = productAdapter
    }

    private fun observeViewModel() {
        homeViewModel.products.observe(this) { products ->
            productAdapter.submitList(products)
        }

        cartViewModel.cartItems.observe(this) { cartItems ->
            val qtyMap = cartItems.associate { it.productId to it.quantity }
            productAdapter.updateCartQuantities(qtyMap)
        }

        cartViewModel.cartItemCount.observe(this) { count ->
            val safeCount = count ?: 0
            tvCartCount.text = safeCount.toString()
            tvCartCount.visibility = if (safeCount > 0)
                android.view.View.VISIBLE else android.view.View.GONE
        }
    }
}
