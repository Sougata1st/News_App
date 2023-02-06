package com.example.myapplication

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&category=science&category=sports&apiKey=0a76255895cd421a93a19482f9ef1816

const val baseurl="https://newsapi.org/"

const val apikey="16af1dcc557540739be0a2d13fc1e142"


interface NewsInterface{
    @GET("v2/top-headlines?apiKey=$apikey")
    fun getHeadlines(@Query("country") country:String, @Query("page") page:Int ,@Query("category") category:String): Call<News>
    //https://newsapi.org/v2/top-headlines?apiKey=0a76255895cd421a93a19482f9ef1816&country=in&category=sports
}
object Newsservice{

    val newsinstance:NewsInterface

    init {

        val retrofit=Retrofit.Builder().baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create()).build()

        newsinstance=retrofit.create(NewsInterface::class.java)

    }

}