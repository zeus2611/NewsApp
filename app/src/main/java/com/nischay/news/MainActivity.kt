package com.nischay.news

import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity(), NewsClicked {
    private var url = "https://saurav.tech/NewsAPI/top-headlines/category/general/in.json"
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = NewsListAdapter(this)
        recyclerView.adapter = mAdapter
    }

    private fun fetchData()  {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for(i in 0 until newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage"),
                    )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        val colorInt = Color.parseColor("#FF3700B3"); //red
        builder.setToolbarColor(colorInt);
        customTabsIntent.launchUrl(this, Uri.parse(item.newsUrl));
    }

    fun technologyNews(view: View){
        url = "https://saurav.tech/NewsAPI/top-headlines/category/technology/in.json"
        fetchData()
    }

    fun businessNews(view: View) {
        url = "https://saurav.tech/NewsAPI/top-headlines/category/business/in.json"
        fetchData()
    }

    fun entertainmentNews(view: View) {
        url = "https://saurav.tech/NewsAPI/top-headlines/category/entertainment/in.json"
        fetchData()
    }

    fun generalNews(view: View) {
        url = "https://saurav.tech/NewsAPI/top-headlines/category/general/in.json"
        fetchData()
    }

    fun healthNews(view: View) {
        url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
        fetchData()
    }

    fun scienceNews(view: View) {
        url = "https://saurav.tech/NewsAPI/top-headlines/category/science/in.json"
        fetchData()
    }

    fun sportsNews(view: View) {
        url = "https://saurav.tech/NewsAPI/top-headlines/category/sports/in.json"
        fetchData()
    }
}