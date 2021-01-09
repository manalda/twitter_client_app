package es.uva.inf.clientetwitter.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import es.uva.inf.clientetwitter.R
import es.uva.inf.clientetwitter.auxiliar.ImpossibleToLoadAccountException
import es.uva.inf.clientetwitter.cliente.TwitterClient
import es.uva.inf.clientetwitter.vista.ajustes.SettingsActivity
import es.uva.inf.clientetwitter.vista.enviartweet.SendStatusActivity

class PanelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tareas)

        val twitter = TwitterClient
        if(!twitter.isAccountLoaded()) {
            try {
                twitter.loadAccount(this)
            } catch(e : ImpossibleToLoadAccountException) {
                changeToPreferenceScreen()
            }
        }

        val sendTweet : Button = findViewById(R.id.sendTweet)
        sendTweet.setOnClickListener {
            startActivity(Intent(this, SendStatusActivity::class.java))
        }

        val ajustes : Button = findViewById(R.id.ajustes)
        ajustes.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        val verTweets : Button = findViewById(R.id.verTweets)
        verTweets.setOnClickListener {
            //TODO
            Log.d("Panel: ", "ver tweets")
        }
    }

    fun changeToPreferenceScreen() {
        Toast.makeText(this, "Introduce los datos de la cuenta", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, SettingsActivity::class.java))
    }
}