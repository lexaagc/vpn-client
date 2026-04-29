package com.barsik.vpn

import android.net.VpnService
import android.content.Intent
import android.os.IBinder

class MyVpnService : VpnService() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}