package com.example.roadrun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class signup_page : AppCompatActivity() {


    private lateinit var mauth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)

        val name_id = findViewById<EditText>(R.id.name_id)
        val reference_id = findViewById<EditText>(R.id.reference_id)
        val email_id = findViewById<EditText>(R.id.email_id)
        val password_id = findViewById<EditText>(R.id.password_id)
        val login_but = findViewById<Button>(R.id.login_but)
        val signup_bt = findViewById<Button>(R.id.signup_but)

        mauth = FirebaseAuth.getInstance()

        val valid_refID = arrayOf( 123456 , 654321 , 234561 , 165432 , 345612 , 216543 )

        login_but.setOnClickListener {

            val intent = Intent(this@signup_page , login_page::class.java)
            startActivity(intent)
            finish()

        }

        signup_bt.setOnClickListener {

            val email = email_id.text.toString()
            val password = password_id.text.toString()
            val name = name_id.text.toString()
            val ref_id = reference_id.text.toString().toIntOrNull()
            val ref_id_txt = reference_id.text.toString()

            var valid_bit : Boolean = false ;

            if( email.isNotEmpty() and password.isNotEmpty() and name.isNotEmpty() and ref_id_txt.isNotEmpty() ) {

                for(vl in valid_refID){
                    if(vl == ref_id){
                        valid_bit=true;
                    }
                }
                if(valid_bit) {
                    signup(email, password)
                }
                else{
                    Toast.makeText(this , "Enter a valid Reference_ID" , Toast.LENGTH_SHORT ).show()
                }
            }
            else{
                Toast.makeText(this , "Enter All the Details" , Toast.LENGTH_SHORT ).show()
            }

        }

    }

    private fun signup(email: String, password: String) {

        mauth.createUserWithEmailAndPassword( email , password)
            .addOnCompleteListener(this) { task->

                if(task.isSuccessful){

                    Toast.makeText(this , "Login Successful" , Toast.LENGTH_SHORT ).show()
                    val intent = Intent(this@signup_page , upload_page::class.java )
                    startActivity(intent)

                }
                else{
                    Toast.makeText(this , "Login Failed, try again !" , Toast.LENGTH_SHORT ).show()
                }

            }

    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        return super.getOnBackInvokedDispatcher()

        val intent = Intent( this , landing_page::class.java)
        startActivity(intent)
    }

}