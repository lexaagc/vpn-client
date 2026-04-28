package com.barsik.vpn
import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
class MainActivity: Activity(){ override fun onCreate(savedInstanceState: Bundle?){ super.onCreate(savedInstanceState); val w=WebView(this); setContentView(w); w.settings.javaScriptEnabled=true; w.addJavascriptInterface(JSBridge(this),'Android'); w.loadUrl('file:///android_asset/index.html') } }