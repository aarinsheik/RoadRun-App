package com.example.roadrun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class upload_page : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_page)

        val sub_but = findViewById<Button>(R.id.submit_but)

        val owner_nm = findViewById<EditText>(R.id.et1)
        val vehicle_nm = findViewById<EditText>(R.id.et2)
        val vehicle_numb = findViewById<EditText>(R.id.et3)
        val fine = findViewById<EditText>(R.id.et4)
        val reason = findViewById<EditText>(R.id.et5)

        sub_but.setOnClickListener {

            val owner_nm_t = owner_nm.text.toString()
            val vehicle_nm_t = vehicle_nm.text.toString()
            val vehicle_numb_t = vehicle_numb.text.toString()
            val fine_t = fine.text.toString()
            val reason_t = reason.text.toString()

            if( owner_nm_t.isNotEmpty() and vehicle_nm_t.isNotEmpty() and vehicle_numb_t.isNotEmpty() and fine_t.isNotEmpty() and reason_t.isNotEmpty() ){
                dbref = FirebaseDatabase.getInstance().getReference("Vechile Info")

                val vehicledata = vehicle_data(owner_nm_t, vehicle_nm_t, vehicle_numb_t, fine_t, reason_t)

                dbref.child(vehicle_numb_t).setValue(vehicledata).addOnSuccessListener {

                    owner_nm.text.clear()
                    vehicle_nm.text.clear()
                    vehicle_numb.text.clear()
                    fine.text.clear()
                    reason.text.clear()

                    Toast.makeText(this , "Details Saved Successfully !" , Toast.LENGTH_LONG).show()

                }

            }
            else{
                Toast.makeText(this , "Please, fill all the details" , Toast.LENGTH_SHORT).show()
            }
        }

    }
}