package com.waytojava.retrofitwithkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // private val TAG: String? = MainActivity::class.simpleName
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSearch.setOnClickListener {

            val searchText = editTextSearchText.text.toString().trim()
            if (TextUtils.isEmpty(searchText)) {
                //Toast.makeText(this, "Please enter searched text", Toast.LENGTH_SHORT).show()
                textViewSearchResult.text = "Please enter searched text"
            } else {
                searchTextAPI(searchText)
            }
        }
    }


    private fun searchTextAPI(searchText: String) {

        val apiService = ApiClient.create()
        disposable =
                apiService.hitCountCheck("query", "json", "search", searchText)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { result ->
                            textViewSearchResult.text = "Total results : ${result.query.searchInfo.totalhits}"
                        },
                        { error ->
                            Toast.makeText(this, "Some Error Occurs", Toast.LENGTH_LONG).show()
                            Log.d("RETROFIT", "Some Error Occurs ${error.message}")
                        }
                    )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
