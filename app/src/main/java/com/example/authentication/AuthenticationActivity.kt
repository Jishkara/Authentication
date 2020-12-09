package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
class AuthenticationActivity : AppCompatActivity() {

    private lateinit var signUpButton: Button
    private lateinit var signInButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        signUpButton = findViewById(R.id.signupButton)
        signInButton = findViewById(R.id.signinButton)

        signUpButton.setOnClickListener {

            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)

        }
        signInButton.setOnClickListener {

            intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }

    }

}

