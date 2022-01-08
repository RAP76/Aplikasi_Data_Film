package com.mws.aplikasidatafilm.activity.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.mws.aplikasidatafilm.R
import com.mws.aplikasidatafilm.activity.MainActivity

class menu_dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_dashboard)
        this.setTitle("Menu")
        klikdata()


    }
    fun klikdata(){
        val pilihmenuData: CardView = findViewById(R.id.cv_datafilm)
        pilihmenuData.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val pilihmenuAbout: CardView = findViewById(R.id.cv_about)
        pilihmenuAbout.setOnClickListener {
            val intent1 = Intent(this, menu_about::class.java)
            startActivity(intent1)
        }
    }
}