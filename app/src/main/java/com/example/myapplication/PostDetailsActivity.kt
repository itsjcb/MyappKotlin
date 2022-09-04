package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PostDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        val tvTitre = findViewById<TextView>(R.id.tvTitle)
        val title = intent.getStringExtra("title")
        tvTitre.text = title
        // providing title for the ActionBar
        supportActionBar!!.title = title
    }
}