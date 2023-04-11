package com.example.watches.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.watches.R
import com.example.watches.databinding.ActivityDetailScreenBinding
import com.example.watches.models.Product

class DetailScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailScreenBinding
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.custom_color)

        product = intent.getSerializableExtra("product") as Product



        Glide.with(this).load(product.imageUrl).into(binding.imgWatchDetailScreen)
        binding.txtWatchNameDetailScreen.text = product.name
        binding.txtWatchPriceDetailScreen.text = "$${product.price}"
        binding.txtDesc.text = product.description
    }
}