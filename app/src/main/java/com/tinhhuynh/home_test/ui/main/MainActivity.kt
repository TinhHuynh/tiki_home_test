package com.tinhhuynh.home_test.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tinhhuynh.home_test.R
import com.tinhhuynh.home_test.data.Product
import com.tinhhuynh.home_test.data.ProductDisplayObject
import com.tinhhuynh.home_test.data.network.tiki.TikiService
import com.tinhhuynh.home_test.utils.ProductHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val tikiService by lazy {
        TikiService.create()
    }

    private var disposable: Disposable? = null
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productHelper: ProductHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }

    // initialize resources
    private fun init() {
        productHelper = ProductHelper()
        initRecyclerView()
        fetchKeywords()
    }

    private fun initRecyclerView() {
        productAdapter = ProductAdapter(this)
        rv_products.adapter = productAdapter
        val dividerItemDecoration = DividerItemDecoration(rv_products.context,
                ( rv_products.layoutManager as LinearLayoutManager).orientation)
        rv_products.addItemDecoration(dividerItemDecoration)
    }

    private fun fetchKeywords() {
        disposable =
                tikiService.fetchProducts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .map {
                            it.map { product ->
                                convertToProductDisplayObject(product)
                            }
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> displayProducts(result) },
                                { error ->
                                    Toast.makeText(this,
                                            "There is an error occurs. Please try again!.",
                                            Toast.LENGTH_LONG).show()
                                }
                        )
    }

    private fun convertToProductDisplayObject(product: Product): ProductDisplayObject {
        return productHelper.convertToDisplayObject(product)
    }

    private fun displayProducts(result: List<ProductDisplayObject>?) {
        result?.apply {
            productAdapter.product = this
        }
    }

}
