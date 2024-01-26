package com.example.roadrun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class landing_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adm_but = findViewById<Button>(R.id.admin_but)
        val user_but = findViewById<Button>(R.id.user_but)

        adm_but.setOnClickListener{

            val intent = Intent(this@landing_page , login_page::class.java )
            startActivity(intent)

        }

        user_but.setOnClickListener{

            val intent = Intent(this@landing_page , user_page::class.java )
            startActivity(intent)

        }


    }
}