package com.tinhhuynh.home_test.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tinhhuynh.home_test.Constants
import com.tinhhuynh.home_test.R
import com.tinhhuynh.home_test.data.network.tiki.TikiService
import com.tinhhuynh.home_test.ui.KeywordDisplayObject
import com.tinhhuynh.home_test.utils.ColorUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import com.tinhhuynh.home_test.utils.KeywordHelper


class MainActivity : AppCompatActivity() {

    private val tikiService by lazy {
        TikiService.create()
    }

    private var disposable: Disposable? = null
    private lateinit var keywordAdapter: KeywordAdapter
    private lateinit var keywordHelper: KeywordHelper

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
        keywordHelper = KeywordHelper()
        initRecyclerView()
        fetchKeywords()
    }

    private fun initRecyclerView() {
        keywordAdapter = KeywordAdapter(this)
        rv_keywords.adapter = keywordAdapter
    }

    private fun fetchKeywords() {
        disposable =
                tikiService.fetchKeywords()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .map {
                            it.map { keyword ->
                                convertToKeywordDisplayObject(keyword)
                            }
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> displayKeywords(result) },
                                { error ->
                                    error.printStackTrace()
                                    Toast.makeText(this,
                                            "There is an error occurs. Please try again!.",
                                            Toast.LENGTH_LONG).show()
                                }
                        )
    }

    private fun convertToKeywordDisplayObject(keyword: String): KeywordDisplayObject {
        val newKeyword = keywordHelper.breakNewLine(keyword)
        val bgColor = ColorUtils.randomMatColor(this, Constants.BG_COLOR_TYPE)
        return KeywordDisplayObject(newKeyword, bgColor)
    }

    private fun displayKeywords(result: List<KeywordDisplayObject>?) {
        result?.apply {
            keywordAdapter.keywords = this
        }
    }

}
