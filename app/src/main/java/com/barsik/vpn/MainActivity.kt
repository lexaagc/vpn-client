package com.barsik.vpn

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keyInput = findViewById<EditText>(R.id.keyInput)
        val pasteBtn = findViewById<Button>(R.id.pasteBtn)
        val startBtn = findViewById<Button>(R.id.startBtn)
        val status = findViewById<TextView>(R.id.statusText)

        // Вставка из буфера
        pasteBtn.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = clipboard.primaryClip
            if (clip != null && clip.itemCount > 0) {
                val text = clip.getItemAt(0).text.toString()
                keyInput.setText(text)
                status.text = "Ключ вставлен"
            }
        }

        // Запуск VPN
        startBtn.setOnClickListener {
            val link = keyInput.text.toString()

            if (link.isEmpty()) {
                status.text = "Вставь ключ!"
                return@setOnClickListener
            }

            val i = Intent(this, MyVpnService::class.java)
            i.putExtra("link", link)
            startService(i)

            status.text = "Подключение..."
        }
    }
}