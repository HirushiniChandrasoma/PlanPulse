package com.example.tma_lab4

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val tvSplashScreen = findViewById<ImageView>(R.id.tvSplashScreen)
        tvSplashScreen.alpha = 0f
        tvSplashScreen.animate().setDuration(1000).alpha(1f).withEndAction{
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }
}