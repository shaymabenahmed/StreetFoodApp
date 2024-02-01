package com.example.streetfoodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class DetailsActivity : AppCompatActivity() {
    lateinit var calculer :Button
    lateinit var commander : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        calculer = findViewById(R.id.calculer)
        commander = findViewById(R.id.commander)

        val food = intent.getParcelableExtra<Food>("food")
        if(food != null){
            val imagedetail : ImageView = findViewById(R.id.detailimage)
            val namedetail : TextView = findViewById(R.id.detailName)
            val prixdetail : TextView = findViewById(R.id.detailprix)

            namedetail.text = food.name
            imagedetail.setImageResource(food.imageTitle)
            prixdetail.text = food.prix.toString()
        }
        calculer.setOnClickListener{
            calculerTotal()
        }

        commander.setOnClickListener{
            commander()
        }
    }


    private fun calculerTotal() : Float {
        // Récupère la valeur du prix depuis le TextView
        val prix = findViewById<TextView>(R.id.detailprix).text.toString().toFloatOrNull() ?: 0f

        // Récupère la valeur de la quantité depuis l'EditText
        val quantite = findViewById<EditText>(R.id.qte).text.toString().toIntOrNull() ?: 0

        // Calcule le résultat (prix * quantité)
        val resultat = prix * quantite

        // Affiche le résultat dans le TextView approprié
        findViewById<TextView>(R.id.total).text = resultat.toString()
        return resultat
    }

    private fun commander() {
        val total = calculerTotal()

        if (total > 0) {
            Toast.makeText(this,"Votre commande est bien passée !!", Toast.LENGTH_LONG).show()
            intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)

        } else {
            Toast.makeText(this,"Merçi de calculer votre total avant de commander !!", Toast.LENGTH_LONG).show()
        }
    }
}