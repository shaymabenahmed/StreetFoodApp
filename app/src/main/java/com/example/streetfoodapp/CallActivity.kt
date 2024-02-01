package com.example.streetfoodapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.streetfoodapp.databinding.ActivityCallBinding

class CallActivity : AppCompatActivity() {
    lateinit var binding:ActivityCallBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.call.setOnClickListener{
            if(binding.tel.text.isNotEmpty()){
                if(ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                )  {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.CALL_PHONE),
                        100
                    );
                }else{
                    val callIntent = Intent(Intent.ACTION_CALL);
                    callIntent.data= Uri.parse("tel: " + binding.tel.text.toString());
                    startActivity(callIntent);
                }
            }else{
                Toast.makeText(this,"Entrer un numéro de telephone valide SVP",Toast.LENGTH_LONG).show();
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