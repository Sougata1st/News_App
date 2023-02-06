package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var article = mutableListOf<Article>()
    lateinit var Adapter: Rnewsadapter
    var pageno = 1
    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5
    var firstVisibleItem = 0
    var visibleItemCount:Int= 0
    var totalItemCount = 0

    //oncreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Adapter = Rnewsadapter(this@MainActivity, article)
        newslist.adapter = Adapter
        newslist.layoutManager = LinearLayoutManager(this@MainActivity)


        newslist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = newslist.getChildCount()
                val layoutManager =
                    LinearLayoutManager::class.java.cast(recyclerView.layoutManager)

                totalItemCount = layoutManager.getItemCount()
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                if (loading) {

                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (!loading && totalItemCount - visibleItemCount
                    <= firstVisibleItem + visibleThreshold
                ) {
                        //
                        pageno++
                        getnews()
                       //
                    loading = true
                }
            }
        })

        getnews()

    }

    private fun getnews() {
        val news = Newsservice.newsinstance.getHeadlines("in", pageno, "sports")
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news = response.body()

                if (news != null) {

                    article.addAll(news.articles)

                    Adapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("sougata", "error")
            }
        })
    }
}