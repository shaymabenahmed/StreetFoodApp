package com.example.streetfoodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser




class SignupActivity : AppCompatActivity() {

    lateinit var mAuth : FirebaseAuth
    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var btn : Button
    lateinit var btn_login : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mAuth=FirebaseAuth.getInstance()
        email=findViewById(R.id.update)
        password=findViewById(R.id.password)
        btn=findViewById(R.id.btn_login)
        btn_login=findViewById(R.id.btn_signin)
        btn.setOnClickListener{
            signUp()
        }
        btn_login.setOnClickListener{
            intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()

        val currentUser : FirebaseUser? = mAuth.currentUser
        if(currentUser != null){
            Toast.makeText(this,"Already conncted !!",Toast.LENGTH_LONG).show()
        }
    }

    fun signUp(){
        mAuth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    val user = mAuth.currentUser
                    Toast.makeText(this,"Your new account is created successfully !!",Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this,"Authentification failed !!",Toast.LENGTH_LONG).show()
                }
            }
    }
}