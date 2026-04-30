package com.barsik.vpn

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var vpnKey: String? = null
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keyStatus = findViewById<TextView>(R.id.keyStatus)
        val pasteBtn = findViewById<Button>(R.id.pasteBtn)
        val startBtn = findViewById<Button>(R.id.startBtn)
        val statusText = findViewById<TextView>(R.id.statusText)

        // 📋 Вставка из буфера
        pasteBtn.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            if (clipboard.hasPrimaryClip()) {
                val text = clipboard.primaryClip?.getItemAt(0)?.text.toString()

                if (text.isNotEmpty()) {
                    vpnKey = text
                    keyStatus.text = "Ключ установлен"
                    Toast.makeText(this, "Ключ вставлен", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Буфер пуст", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // ▶️ Старт / стоп
        startBtn.setOnClickListener {

            if (vpnKey.isNullOrEmpty()) {
                Toast.makeText(this, "Сначала вставь ключ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isRunning) {
                val intent = Intent(this, MyVpnService::class.java)
                intent.putExtra("link", vpnKey)

                startService(intent)

                statusText.text = "VPN подключен"
                startBtn.text = "Стоп"
                isRunning = true

            } else {
                stopService(Intent(this, MyVpnService::class.java))

                statusText.text = "VPN остановлен"
                startBtn.text = "Старт"
                isRunning = false
            }
        }
    }
}