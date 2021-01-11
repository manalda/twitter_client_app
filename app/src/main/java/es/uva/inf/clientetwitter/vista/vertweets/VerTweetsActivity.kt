@file:Suppress("DEPRECATION")

package es.uva.inf.clientetwitter.vista.vertweets

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity
import es.uva.inf.clientetwitter.R
import es.uva.inf.clientetwitter.auxiliar.Constantes
import es.uva.inf.clientetwitter.auxiliar.TimeStampConverter

class VerTweetsActivity : AppCompatActivity() {
    private var tweetsContainer : ListView? = null
    private val controlador = ControladorVerTweets(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_tweets)

        val borrarLista : Button = findViewById(R.id.borrarDatos)
        borrarLista.setOnClickListener {
            controlador.deleteTimeline()
        }

        tweetsContainer = findViewById(R.id.tweets_container)
        tweetsContainer?.emptyView = findViewById(R.id.emptyContainer)
        loadTweets()
    }

    private fun loadTweets() {
        val cursor = controlador.getTimeline()
        val from = arrayOf(Constantes.USUARIO_TWEET_TAG, Constantes.CREATED_AT_TWEET_TAG, Constantes.MESSAGE_TWEET_TAG)
        val to = intArrayOf(R.id.item_user, R.id.item_created_at, R.id.item_message)

        val adapter = SimpleCursorAdapter(this, R.layout.tweet_list_item, cursor, from, to)
        adapter.viewBinder = TimeStampConverter

        tweetsContainer?.adapter = adapter
    }

    fun reloadTimeline() {
        startActivity(Intent(this, VerTweetsActivity::class.java))
    }
}