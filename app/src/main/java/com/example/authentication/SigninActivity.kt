package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.example.authentication.R
import com.google.firebase.auth.ktx.*
import com.example.authentication.AuthenticationActivity
import com.google.firebase.auth.ktx.auth

class SigninActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var mailInputET: EditText
    private lateinit var passwordInputText: EditText
    private lateinit var passwordInput:String
    private lateinit var emailInput:String
    private lateinit var progressBar1: ProgressBar
    private lateinit var auth: FirebaseAuth





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        loginButton = findViewById(R.id.loginButton)
        mailInputET = findViewById(R.id.mailInputET)
        passwordInputText = findViewById(R.id.editTextTextPasswordInput)
        passwordInput = passwordInputText.text.toString()
        emailInput = mailInputET.text.toString()
        progressBar1 = findViewById(R.id.progressBar1)
        auth = Firebase.auth
        progressBar1.visibility = View.GONE
        loginButton.setOnClickListener {
            val email : String = mailInputET.text.toString()
            val password : String = passwordInputText.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (email.contains("@") && email.contains(".")){
                    progressBar1.visibility = View.VISIBLE
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this) { task ->
                                progressBar1.visibility = View.GONE
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    d("logIn", "signInWithEmail:success")
                                    Toast.makeText(baseContext, "Authentication success.",
                                            Toast.LENGTH_SHORT).show()
                                    val user = auth.currentUser
                                    val intent = Intent(this,AuthenticationActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                } else {
                                    // If sign in fails, display a message to the user.
                                    d("logIn", "signInWithEmail:failure", task.exception)
                                    Toast.makeText(baseContext, "Authentication failed." + task.exception,
                                            Toast.LENGTH_SHORT).show()
                                }
                                }
                            }

                }else {
                    Toast.makeText(this, "Email Format Is Not Correct", Toast.LENGTH_SHORT).show()
                }
            }

        }


}
