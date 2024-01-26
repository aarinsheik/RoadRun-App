package com.example.roadrun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class login_page : AppCompatActivity() {

    private lateinit var mauth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val email_id = findViewById<EditText>(R.id.email_id)
        val password_id = findViewById<EditText>(R.id.password_id)
        val login_but = findViewById<Button>(R.id.login_but)
        val signup_but = findViewById<Button>(R.id.signup_but)

        mauth = FirebaseAuth.getInstance()

        signup_but.setOnClickListener {

            val intent = Intent(this@login_page , signup_page::class.java)
            startActivity(intent)
            finish()

        }

        login_but.setOnClickListener {

            val email = email_id.text.toString()
            val password = password_id.text.toString()

            if( email.isNotEmpty() and password.isNotEmpty() ) {
                login(email, password)
            }
            else{
                Toast.makeText(this , "Enter Email and Password " , Toast.LENGTH_SHORT ).show()
            }

        }

    }

    private fun login(email: String, password: String) {

        mauth.signInWithEmailAndPassword( email , password)
            .addOnCompleteListener(this) { task->

                if(task.isSuccessful){

                    Toast.makeText(this , "Login Successful" , Toast.LENGTH_SHORT ).show()
                    val intent = Intent(this@login_page , upload_page::class.java )
                    startActivity(intent)

                }
                else{
                    Toast.makeText(this , "Login Failed, try again !" , Toast.LENGTH_SHORT ).show()
                }

            }

    }
}