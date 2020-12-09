package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.example.authentication.R
import com.google.firebase.auth.ktx.*



class SignupActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var mailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var passwordEditText1: EditText
    private lateinit var registerButton: Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mailEditText = findViewById(R.id.editTextTextPersonMail)
        passwordEditText = findViewById(R.id.editTextTextPersonPassword)
        passwordEditText1 = findViewById(R.id.editTextTextPersonPassword1)
        registerButton = findViewById(R.id.registerButton)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.GONE
        auth = Firebase.auth
        registerButton.setOnClickListener {

            val email : String = mailEditText.text.toString()
            val password : String = passwordEditText.text.toString()
            val password1 : String = passwordEditText1.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && password1.isNotEmpty()) {
                if (email.contains("@") && email.contains(".")){
                    if (password == password1) {
                        progressBar.visibility = View.VISIBLE
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this) { task ->
                                progressBar.visibility = View.GONE
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Signup", "createUserWithEmail:success")
                                    val user = auth.currentUser
                                    intent = Intent(this, SigninActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Signup", "createUserWithEmail:failure", task.exception)
                                    Toast.makeText(baseContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show()
                                }

                                // ...
                            }
                    } else{
                        Toast.makeText(this, "Please Enter Exactly Same Passwords", Toast.LENGTH_SHORT).show()
                    }
                }else {
                    Toast.makeText(this, "Email Format Is Not Correct", Toast.LENGTH_SHORT).show()
                }
            } else{
                Toast.makeText(this, "Please Fill Out All Fields", Toast.LENGTH_SHORT).show()
            }

        }
    }

}