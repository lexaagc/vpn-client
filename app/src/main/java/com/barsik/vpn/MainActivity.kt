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

        val statusText = findViewById<TextView>(R.id.statusText)
        val pasteBtn = findViewById<Button>(R.id.pasteBtn)
        val startBtn = findViewById<Button>(R.id.startBtn)

        // Вставка ключа из буфера
        pasteBtn.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            if (clipboard.hasPrimaryClip()) {
                val item = clipboard.primaryClip?.getItemAt(0)
                val text = item?.text?.toString()

                if (!text.isNullOrEmpty()) {
                    vpnKey = text
                    statusText.text = "Ключ установлен"
                } else {
                    statusText.text = "Буфер пуст"
                }
            } else {
                statusText.text = "Буфер пуст"
            }
        }

        // Старт VPN
        startBtn.setOnClickListener {
            if (vpnKey.isNullOrEmpty()) {
                statusText.text = "Сначала вставь ключ"
                return@setOnClickListener
            }

            statusText.text = "Подключение..."

            // TODO: запуск VPN сервиса
        }
    }
}