package com.example.watches.views


import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.watches.R
import com.example.watches.adapter.ProductAdapter
import com.example.watches.databinding.ActivityHomeScreenBinding
import com.example.watches.viewmodels.ProductViewModel
import com.google.firebase.FirebaseApp


class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding

    private lateinit var productViewModel: ProductViewModel
    private lateinit var productListRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)

        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.custom_color)


        productListRecyclerView = binding.recViewWatches

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        productAdapter = ProductAdapter(listOf()) {
            val intent = Intent(this, DetailScreenActivity::class.java)
            intent.putExtra("product", it)
            startActivity(intent)
        }
        productListRecyclerView.adapter = productAdapter
        productViewModel.getAllProducts()


        binding.btn1.setOnClickListener {
            productViewModel.getProductsByCategory("smart watch")
        }
        binding.btn2.setOnClickListener {
            productViewModel.getProductsByCategory("casio")
        }
        binding.btn3.setOnClickListener {
            productViewModel.getProductsByCategory("tisto")
        }
        binding.btn4.setOnClickListener {
            productViewModel.getProductsByCategory("siko")
        }

        productViewModel.products.observe(this) { products ->
            productAdapter.updateProducts(products)
        }
    }
}