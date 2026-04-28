package com.barsik.vpn
import android.net.VpnService
import android.content.Intent
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
class MyVpnService: VpnService(){ override fun onStartCommand(i:Intent?,f:Int,s:Int):Int{ val link=i?.getStringExtra('link')?:return START_NOT_STICKY; val b=Builder(); b.addAddress('10.0.0.2',32); b.addRoute('0.0.0.0',0); b.setSession('BarsikVPN'); b.establish(); val u=Uri.parse(link); val cfg="{\"outbounds\":[{\"protocol\":\"vless\",\"settings\":{\"vnext\":[{\"address\":\"${u.host}\",\"port\":${u.port},\"users\":[{\"id\":\"${u.userInfo}\",\"encryption\":\"none\"}]}]}}]}"; val cf=File(filesDir,'config.json'); cf.writeText(cfg); val bin=File(filesDir,'xray'); if(!bin.exists()){ assets.open('xray').use{inp-> FileOutputStream(bin).use{out-> inp.copyTo(out)} }; bin.setExecutable(true) } Runtime.getRuntime().exec("${bin.absolutePath} -config ${cf.absolutePath}"); return START_STICKY } override fun onDestroy(){ super.onDestroy(); android.os.Process.killProcess(android.os.Process.myPid()) } }