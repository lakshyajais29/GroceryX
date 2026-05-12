package com.example.groceryx.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.groceryx.data.model.Product
import com.example.groceryx.utils.Constants

class HomeViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Product>>(Constants.PRODUCTS)
    val products: LiveData<List<Product>> = _products

    private val _selectedCategory = MutableLiveData("All")
    val selectedCategory: LiveData<String> = _selectedCategory

    private var searchQuery = ""
    private var activeCategory = "All"

    fun filterByCategory(category: String) {
        activeCategory = category
        _selectedCategory.value = category
        applyFilters()
    }

    fun search(query: String) {
        searchQuery = query
        applyFilters()
    }

    private fun applyFilters() {
        var list = Constants.PRODUCTS

        if (activeCategory != "All") {
            list = list.filter { it.category == activeCategory }
        }

        if (searchQuery.isNotEmpty()) {
            list = list.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                it.category.contains(searchQuery, ignoreCase = true)
            }
        }

        _products.value = list
    }
}
