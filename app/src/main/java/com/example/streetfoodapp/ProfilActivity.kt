package com.example.streetfoodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class ProfilActivity : AppCompatActivity() {

    lateinit var mAuth : FirebaseAuth
    lateinit var id : TextView
    lateinit var email : TextView
    lateinit var name : EditText
    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        mAuth = FirebaseAuth.getInstance()
        id = findViewById(R.id.id)
        email = findViewById(R.id.mail)
        name = findViewById(R.id.name)
        btn = findViewById(R.id.update)
        btn.setOnClickListener{
            Update()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser : FirebaseUser? = mAuth.currentUser
        if(currentUser != null){
            val user : FirebaseUser? = mAuth.currentUser
            id.text = user?.uid
            email.text = user?.email
            name.setText(user?.displayName)
        }
    }

    fun Update(){
        var user = FirebaseAuth.getInstance().currentUser
        var profilUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(name.text.toString()).build()
        user?.updateProfile(profilUpdates)?.addOnCompleteListener{task ->
            if(task.isSuccessful){
                val user = mAuth.currentUser
                Toast.makeText(this,"User Profile Updated", Toast.LENGTH_LONG).show()
                intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"User Profile Not Updated !!", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.home -> true
            R.id.contact -> {
                val intent = Intent(this, CallActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.profil -> {
                val intent = Intent(this, ProfilActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}