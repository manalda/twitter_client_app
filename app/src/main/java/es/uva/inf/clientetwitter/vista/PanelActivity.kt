package es.uva.inf.clientetwitter.vista

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.uva.inf.clientetwitter.R
import es.uva.inf.clientetwitter.auxiliar.excepciones.ImpossibleToLoadAccountException
import es.uva.inf.clientetwitter.auxiliar.servicios.RefreshService
import es.uva.inf.clientetwitter.cliente.TwitterClient
import es.uva.inf.clientetwitter.vista.ajustes.SettingsActivity
import es.uva.inf.clientetwitter.vista.enviartweet.SendStatusActivity
import es.uva.inf.clientetwitter.vista.vertweets.VerTweetsActivity


class PanelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tareas)

        loadAccountSettings()

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
            startActivity(Intent(this, VerTweetsActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.start_service -> {
                val intent = Intent(this, RefreshService::class.java)
                intent.action = RefreshService.ACTION_FETCH_TIMELINE
                startService(intent)
            }
            R.id.stop_service -> {
                stopService(Intent(this, RefreshService::class.java))
            }
        }

        return true
    }
    private fun changeToPreferenceScreen() {
        Toast.makeText(this, "Introduce los datos de la cuenta", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun loadAccountSettings() {
        if(!TwitterClient.isAccountLoaded()) {
            try {
                TwitterClient.loadAccount(this)
            } catch (e: ImpossibleToLoadAccountException) {
                changeToPreferenceScreen()
            }
        }
    }
}