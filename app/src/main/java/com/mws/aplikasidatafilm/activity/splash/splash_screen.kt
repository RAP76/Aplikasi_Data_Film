package com.mws.aplikasidatafilm.activity.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mws.aplikasidatafilm.R
import com.mws.aplikasidatafilm.activity.MainActivity
import com.mws.aplikasidatafilm.activity.login.login_activity

class splash_screen : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, login_activity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}