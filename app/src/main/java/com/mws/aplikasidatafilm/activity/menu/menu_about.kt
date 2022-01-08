package com.mws.aplikasidatafilm.activity.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mws.aplikasidatafilm.R

class menu_about : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_about)
        this.setTitle("About")

       back()
    }
    private fun back(){
        val btnback: Button = findViewById(R.id.btn_back)
        btnback.setOnClickListener {
            finish()
        }
    }
    }
