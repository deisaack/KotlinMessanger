package com.addictaf.kotlinmessenger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
//import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            performLogin()
        }

        back_to_register_textview_login.setOnClickListener {
            finish()
        }
    }


    private fun performLogin() {
        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()

        Log.d("dlogin", "Email: $email, Password: $password")

        if (email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this, "Please fill in email and password", Toast.LENGTH_SHORT).show()
            return
        }


        // Firebase authentication to login user with email and password
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                // Else if successfull
                Log.d("dlogin", "Successful")
            }
            .addOnFailureListener {
                Log.d("dlogin", "Failed to login user ${it.message}")
                Toast.makeText(this, "Failled: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
