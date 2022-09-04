package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences("app_state", Context.MODE_PRIVATE)

        val isAuthentificated = sharedPreferences.getBoolean("is_authentificated", false)
        // récuperer the email
        val emailSharedPreferences = sharedPreferences.getString("email", "")
        //if connected before pass the activity
        if (isAuthentificated){
            Intent(this, HomeActivity::class.java).also {
                it.putExtra("email", emailSharedPreferences)
                startActivity(it)
            }
        }
        val connect = findViewById<Button>(R.id.btnConnect)
        val email = findViewById<TextView>(R.id.etEmail)
        val pwd = findViewById<TextView>(R.id.etPwd)
        val error = findViewById<TextView>(R.id.etError)

        connect.setOnClickListener {
            //check after you hide
            error.visibility = View.GONE

            val txtEmail = email.text.toString()
            val txtPwd = pwd.text.toString()

            if(txtEmail.trim().isEmpty() || txtPwd.trim().isEmpty()){
                error.text = "All field must be filled!"
                //to make it visible
                error.visibility = View.VISIBLE
                //Toast.makeText(this, "All must be filled", Toast.LENGTH_LONG).show()
            }
            else{
                val ctxtEmail = "jcb@gmail.com"
                val ctxtPwd = "1234"

                if (ctxtEmail.equals(txtEmail) && ctxtPwd.equals(txtPwd)){
                    //empty at every time you go back
                    //Toast.makeText(this,"You can now use the app!", Toast.LENGTH_LONG).show()
                    email.setText("")
                    pwd.setText("")

                    // *Save Boolean isAuthentificated in sharedPref. After authentication
                    // open an editor
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("is_authentificated", true)
                    editor.putString("email", txtEmail)
                    editor.apply()

                    //Explicit intent: 1st way
                    val intentTohome = Intent(this, HomeActivity::class.java)
                       //send to the second activity
                    intentTohome.putExtra("email", txtEmail)
                    startActivity(intentTohome)
                    //Explicit intent: 2nd way
                    /*
                    Intent(this, HomeActivity::class.java).also {
                        it.putExtra("email",txtEmail)
                        startActivity(it)
                    }*/
                    //Explicit intent: 3rd way
                    // :: est utilisée pour appeler une méthode en
                    // s'y référant directement à l'aide de sa classe
                    /*val intentTohome = Intent(this, HomeActivity::class.java)
                    intentTohome.apply {
                        putExtra("email",txtEmail)
                        startActivity(this)
                    }*/

                }
                else{
                    error.text = "Email and password not correct!"
                    error.visibility = View.VISIBLE
                    //Toast.makeText(this, "Bad email and password", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}