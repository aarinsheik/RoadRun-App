package com.example.roadrun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.roadrun.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class user_page : AppCompatActivity() {

    private lateinit var storageref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page)

        val but = findViewById<Button>(R.id.sub_but)

        but.setOnClickListener {
            var v_no = findViewById<EditText>(R.id.vnoET).text.toString()

            if(v_no.isNotEmpty() ) {
                read_data(v_no)
            }
            else{
                Toast.makeText(this,"Please, Enter the Vehicle Number.",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun read_data( vno : String){

        storageref = FirebaseDatabase.getInstance().getReference("Vechile Info")
        storageref.child(vno).get().addOnSuccessListener {
            if( it.exists() ){

                val onwner_name :String? =  it.child("owner_name").value.toString()
                val vehicle_name :String? =  it.child("vehicle_name").value.toString()
                val fine :String? =  it.child("fine_amount").value.toString()
                val reason :String? =  it.child("reason").value.toString()

                Toast.makeText(this,"Vehicle Found !",Toast.LENGTH_SHORT).show()

                var vnoET = findViewById<EditText>(R.id.vnoET)
                var u_nm = findViewById<TextView>(R.id.usr_nm)
                var u_vnm = findViewById<TextView>(R.id.usr_vnm)
                var u_fne = findViewById<TextView>(R.id.usr_fine)
                var u_res = findViewById<TextView>(R.id.usr_reason)

                vnoET.text.clear()

                u_nm.text = onwner_name
                u_vnm.text = vehicle_name
                u_fne.text = fine
                u_res.text = reason

                var layout = findViewById<LinearLayout>(R.id.ll1)
                layout.visibility = View.VISIBLE

            }
            else{
                Toast.makeText(this,"Vehicle Does not exist !",Toast.LENGTH_SHORT).show()
            }
        }
            .addOnFailureListener {
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
    }
}