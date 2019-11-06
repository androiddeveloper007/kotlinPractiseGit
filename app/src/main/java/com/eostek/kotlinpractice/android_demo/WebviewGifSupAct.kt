package com.eostek.kotlinpractice.android_demo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.eostek.kotlinpractice.R
import kotlinx.android.synthetic.main.webview.*

class WebviewGifSupAct:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview)
//        webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);// 禁止硬件加速
        webview.setBackgroundColor(Color.TRANSPARENT);
        webview.settings.useWideViewPort = true
        webview.settings.loadWithOverviewMode = true
        webview.loadDataWithBaseURL(
            null,
            "<HTML><body><div><IMG src='file:///android_asset/aa.gif'/></div></body></html>",
            "text/html", "UTF-8", null)
        another(webview1)
        another(webview2)
        another(webview3)
    }

    private fun another(webview: WebView) {
        webview.setBackgroundColor(Color.TRANSPARENT);
        webview.settings.useWideViewPort = true
        webview.settings.loadWithOverviewMode = true
        webview.loadDataWithBaseURL(
            null,
            "<HTML><body><div><IMG src='file:///android_asset/aa.gif'/></div></body></html>",
            "text/html", "UTF-8", null)
    }
}