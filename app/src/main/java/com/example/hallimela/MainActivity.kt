package com.example.hallimela

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ProductAdapter
    private lateinit var productList: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val searchView = findViewById<SearchView>(R.id.searchView)

        productList = listOf(
            Product("Clay Pot", "₹250", "Traditional handmade clay pot from local artisans.", R.drawable.claypot),
            Product("Bamboo Basket", "₹180", "Eco-friendly hand-woven bamboo basket for daily use.", R.drawable.basket),
            Product("Terracotta Lamp", "₹150", "Decorative handcrafted lamp for an aesthetic home vibe.", R.drawable.lamp),
            Product("Handwoven Saree", "₹1800", "Authentic village handloom saree with intricate designs.", R.drawable.saree),
            Product("Laddu", "₹80", "Nutritious and healthy homemade sweets made with pure ghee.", R.drawable.laddu),
            Product("Toy Cart", "₹320", "Traditional handmade wooden toy cart for kids.", R.drawable.toy),
            Product("Organic Jaggery", "₹120", "Pure, chemical-free organic jaggery (Bella).", R.drawable.jaggery), // Placeholder image
            Product("Handmade Soap", "₹60", "Natural herbal soap made with neem and aloe vera.", R.drawable.soap), // Placeholder
            Product("Copper Water Jug", "₹850", "Traditional copper vessel for health benefits.", R.drawable.bootle), // Placeholder
            Product("Jute Carry Bag", "₹150", "Strong and stylish eco-friendly jute bag.", R.drawable.jute), // Placeholder
            Product("Spicy Mango Pickle", "₹90", "Grandma's secret recipe mango pickle.", R.drawable.pickle), // Placeholder
            Product("Incense Sticks", "₹45", "Hand-rolled natural fragrance agarbatti.", R.drawable.sticks) // Placeholder
        )
        recyclerView.isNestedScrollingEnabled = false
        adapter = ProductAdapter(productList) { product ->
            showProductDialog(product)
        }

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = productList.filter {
                    it.name.contains(newText ?: "", true)
                }
                adapter.filterList(filteredList)
                return true
            }
        })
    }

    private fun showProductDialog(product: Product) {
        AlertDialog.Builder(this)
            .setTitle(product.name)
            .setMessage("Price: ${product.price}\n\n${product.description}")
//            .setPositiveButton("Order Now") { _, _ ->
//                // Placeholder for order logic
//            }
            .setNegativeButton("Close", null)
            .show()
    }
}
