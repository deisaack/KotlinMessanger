package com.addictaf.kotlinmessenger

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_button_register.setOnClickListener {
            performRegister()
        }

        already_have_an_account_textview.setOnClickListener {
            Log.d("MainActivity", "Try to show login activity")

            // Launch the login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performRegister() {
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        if (email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this, "Please fill in email and password", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("MainActivity", "Email: "+ email)
        Log.d("MainActivity", "Password: $password")

        // Firebase authentication to create user with email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                // Else if successfull
                Log.d("MAIN", "Created user with uid ${it.result?.user?.uid}")
            }
            .addOnFailureListener {
                Log.d("MAIN", "Failed to create user ${it.message}")
                Toast.makeText(this, "Failled: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
