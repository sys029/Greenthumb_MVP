package com.example.greenthumb_tester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductList : AppCompatActivity(),IProductView {

    private val productPresenter=ProductPresenter(this,this)

    private val dataList: MutableList<ProductData> = mutableListOf()
    private lateinit var myAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        myAdapter = ProductAdapter(dataList)

        recycler_product.layoutManager = GridLayoutManager(this,2)
        recycler_product.adapter = myAdapter

        toolbarItem.title=""
        setSupportActionBar(toolbarItem)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        productPresenter.callProductApi()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item, menu)
        return true
    }

    override fun onProductSuccess(loginBase: ProductResponse) {
        if (loginBase.status == resources.getString(R.string.success)) {

            dataList.addAll(loginBase.data)
            myAdapter.notifyDataSetChanged()
        }
    }

    override fun onProductError(error: Error) {
        Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
    }
}
