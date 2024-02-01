package com.example.streetfoodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    lateinit var mAuth : FirebaseAuth
    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        btn = findViewById(R.id.update)
        btn.setOnClickListener{
            Signin()
        }
        }


  /*  override fun onStart() {
        super.onStart()

        val currentUser : FirebaseUser? = mAuth.currentUser
        if(currentUser != null){
            intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }*/

    fun Signin(){
        mAuth.signInWithEmailAndPassword(email.text.toString(),password.text.toString())
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    val user : FirebaseUser? = mAuth.currentUser
                    Toast.makeText(this,"Success Authentification !!",Toast.LENGTH_LONG).show()
                    intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Authentification failed !!",Toast.LENGTH_LONG).show()
                }
            }
}
}