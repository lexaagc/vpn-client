package com.barsik.vpn
import android.content.Context
import android.content.Intent
import android.webkit.JavascriptInterface
class JSBridge(val c: Context){ @JavascriptInterface fun connectVPN(link:String){ val i=Intent(c,MyVpnService::class.java); i.putExtra('link',link); c.startService(i)} @JavascriptInterface fun disconnectVPN(){ c.stopService(Intent(c,MyVpnService::class.java)) } }