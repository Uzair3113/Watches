package com.example.watches.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watches.models.Product
import com.example.watches.repo.ProductRepository

class ProductViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    fun getProductsByCategory(category: String) {
        repository.getProductsByCategory(category) { products ->
            _products.value = products
        }
    }

    fun getAllProducts() {

        repository.getAllProducts { products ->
            _products.value = products
        }
    }
}
