package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tvHello = findViewById<TextView>(R.id.tvHello)

        //Get the email sent by MainActivity
        val email = intent.getStringExtra("email")
        //Display the mail @
        tvHello.text = "Welcome : $email"
    }
}