package com.barsik.vpn

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var vpnKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keyStatus = findViewById<TextView>(R.id.keyStatus)
        val statusText = findViewById<TextView>(R.id.statusText)
        val pasteBtn = findViewById<Button>(R.id.pasteBtn)
        val startBtn = findViewById<Button>(R.id.startBtn)

        pasteBtn.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val text = clipboard.primaryClip?.getItemAt(0)?.text?.toString()

            if (!text.isNullOrEmpty()) {
                vpnKey = text
                keyStatus.text = "Ключ установлен"
            } else {
                keyStatus.text = "Буфер пуст"
            }
        }

        startBtn.setOnClickListener {
            if (vpnKey.isNullOrEmpty()) {
                statusText.text = "Сначала вставь ключ"
                return@setOnClickListener
            }

            statusText.text = "Подключение..."
        }
    }
}
