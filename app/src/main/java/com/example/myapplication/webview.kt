package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_webview.*

class webview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        getSupportActionBar()?.hide()
        val url=intent.getStringExtra("URL")
        if (url!=null){
            webView.settings.javaScriptEnabled=true
            webView.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    webView.settings.userAgentString="Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"
                    super.onPageFinished(view, url)
                    progressBar.visibility=View.GONE
                    webView.visibility=View.VISIBLE
                }
            }
            webView.loadUrl(url)
        }
    }
}