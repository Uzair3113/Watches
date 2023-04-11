package com.example.watches.repo

import com.example.watches.models.Product
import com.google.firebase.firestore.FirebaseFirestore

class ProductRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun getProductsByCategory(category: String, callback: (List<Product>) -> Unit) {
        firestore.collection("products")
            .whereEqualTo("category", category)
            .get()
            .addOnSuccessListener { result ->
                val products = mutableListOf<Product>()
                for (document in result) {
                    val product = document.toObject(Product::class.java)
                    products.add(product)
                }
                callback(products)
            }
    }

    fun getAllProducts(callback: (List<Product>) -> Unit) {
        firestore.collection("products")
            .get()
            .addOnSuccessListener { result ->
                val products = mutableListOf<Product>()
                for (document in result) {
                    val product = document.toObject(Product::class.java)
                    products.add(product)
                }
                callback(products)
            }
    }
}

