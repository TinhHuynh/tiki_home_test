package com.tinhhuynh.home_test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tinhhuynh.home_test.data.network.tiki.TikiService
import com.tinhhuynh.home_test.ui.main.KeywordAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import com.tinhhuynh.home_test.ui.main.KeywordItemDecoration


class MainActivity : AppCompatActivity() {

    val tikiService by lazy {
        TikiService.create()
    }

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    // initialize resources
    private fun init() {
        initRecyclerView()
        fetchKeywords()
    }


    private fun initRecyclerView() {
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_keyword_item)
        rv_keywords.addItemDecoration(KeywordItemDecoration(spacingInPixels))
    }

    private fun fetchKeywords() {
        disposable =
                tikiService.fetchKeywords()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> displayKeywords(result) },
                                { error ->
                                    Toast.makeText(this,
                                            "There is an error occurs. Please try again!.",
                                            Toast.LENGTH_LONG).show()
                                }
                        )
    }

    private fun displayKeywords(result: List<String>?) {
        rv_keywords.adapter = KeywordAdapter(result as ArrayList<String>, this)
    }

}
