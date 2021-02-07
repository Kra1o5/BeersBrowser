package com.android.beersbrowser.view


import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.beersbrowser.R
import com.android.beersbrowser.model.BeerInfo
import com.bumptech.glide.Glide


class DetailActivity : AppCompatActivity() {
    private var context: Context? = null
    private var beer: BeerInfo? = null
    private var productImage: ImageView? = null
    private var productName: TextView? = null
    private var productABV: TextView? = null
    private var productDescription: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        context = applicationContext
        beer = BeerInfo()
        if (intent.hasExtra("beer")) {
            beer = intent.extras!!.getSerializable("beer") as BeerInfo?
        }
        productImage = findViewById(R.id.product_image)
        productName = findViewById(R.id.product_name)
        productABV = findViewById(R.id.product_abv)
        productDescription = findViewById(R.id.product_description)
        val imageContext = productImage!!.context
        Glide.with(imageContext)
            .load(beer!!.imageURL)
            .into(productImage!!)
        productName!!.text = beer!!.name
        productABV!!.text = beer!!.abv.toString()
        productDescription!!.text = beer!!.description
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}