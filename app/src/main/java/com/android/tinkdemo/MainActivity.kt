package com.android.tinkdemo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    findViewById<Button>(R.id.open_tink_activity_button).setOnClickListener {
      startActivity(TinkActivity.intent(this, "WIZARD_SESSION_KEY_FROM_BACKEND"))
    }
  }
}