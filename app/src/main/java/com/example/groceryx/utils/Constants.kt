package com.example.groceryx.utils

import com.example.groceryx.R
import com.example.groceryx.data.model.Category
import com.example.groceryx.data.model.Product

object Constants {

    const val FAKE_OTP = "1234"
    const val PREF_NAME = "OceanXPrefs"
    const val KEY_MOBILE = "mobile_number"
    const val KEY_IS_LOGGED_IN = "is_logged_in"
    const val FREE_DELIVERY_THRESHOLD = 199
    const val DELIVERY_CHARGE = 30
    const val ESTIMATED_DELIVERY = "10 minutes"

    val CATEGORIES = listOf(
        Category(0, "All",                  R.drawable.ic_all),
        Category(1, "Fruits & Vegetables",  R.drawable.ic_fruits),
        Category(2, "Dairy & Eggs",         R.drawable.ic_dairy),
        Category(3, "Snacks",               R.drawable.ic_snacks),
        Category(4, "Beverages",            R.drawable.ic_beverages),
        Category(5, "Bakery",               R.drawable.ic_bakery),
        Category(6, "Cleaning",             R.drawable.ic_cleaning)
    )

    val PRODUCTS = listOf(
        // Fruits & Vegetables
        Product(1,  "Fresh Banana",         "Fruits & Vegetables", 29,  "500g",  R.drawable.img_banana),
        Product(2,  "Tomato",               "Fruits & Vegetables", 35,  "1kg",   R.drawable.img_tomato),
        Product(3,  "Onion",                "Fruits & Vegetables", 40,  "1kg",   R.drawable.img_onion),
        Product(4,  "Spinach",              "Fruits & Vegetables", 20,  "250g",  R.drawable.img_spinach),
        // Dairy & Eggs
        Product(5,  "Amul Milk",            "Dairy & Eggs",        28,  "500ml", R.drawable.img_milk),
        Product(6,  "Amul Butter",          "Dairy & Eggs",        56,  "100g",  R.drawable.img_butter),
        Product(7,  "Farm Eggs",            "Dairy & Eggs",        72,  "6pcs",  R.drawable.img_eggs),
        // Snacks
        Product(8,  "Lays Classic",         "Snacks",              20,  "26g",   R.drawable.img_lays),
        Product(9,  "Kurkure Masala",       "Snacks",              20,  "90g",   R.drawable.img_kurkure),
        Product(10, "Hide & Seek Biscuit",  "Snacks",              30,  "100g",  R.drawable.img_hideseek),
        // Beverages
        Product(11, "Coca Cola",            "Beverages",           40,  "750ml", R.drawable.img_coke),
        Product(12, "Tropicana Orange",     "Beverages",           99,  "1L",    R.drawable.img_tropicana),
        Product(13, "Red Bull",             "Beverages",           125, "250ml", R.drawable.img_redbull),
        // Bakery
        Product(14, "Britannia Bread",      "Bakery",              45,  "400g",  R.drawable.img_bread),
        Product(15, "Croissant",            "Bakery",              35,  "1pc",   R.drawable.img_croissant),
        Product(16, "Muffin Choco",         "Bakery",              55,  "75g",   R.drawable.img_muffin),
        // Cleaning
        Product(17, "Surf Excel",           "Cleaning",            120, "500g",  R.drawable.img_surfexcel),
        Product(18, "Vim Bar",              "Cleaning",            25,  "155g",  R.drawable.img_vim),
        Product(19, "Harpic Cleaner",       "Cleaning",            99,  "500ml", R.drawable.img_harpic),
        Product(20, "Lizol Floor",          "Cleaning",            185, "500ml", R.drawable.img_lizol)
    )

    val BANNERS = listOf(
        "🥦 Fresh Veggies at ₹29!",
        "⚡ 10 Min Delivery!",
        "🚚 Free delivery above ₹199"
    )
}
