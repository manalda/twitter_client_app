@file:Suppress("DEPRECATION")

package es.uva.inf.clientetwitter.auxiliar.servicios

import es.uva.inf.clientetwitter.persistencia.DBHelper
import android.app.IntentService
import android.content.Intent
import android.widget.Toast
import es.uva.inf.clientetwitter.auxiliar.excepciones.ReadTimelineException
import es.uva.inf.clientetwitter.cliente.TwitterClient
import es.uva.inf.clientetwitter.persistencia.DBConnection


class RefreshService : IntentService("RefreshService") {
    companion object {
        const val ACTION_FETCH_TIMELINE = "es.uva.inf.clientetwitter.auxiliar.servicios.action.FETCH_TIMELINE"
        const val DELAY = 30000L
    }

    private var runFlag = false
    private val db = DBConnection
    private val helper = DBHelper(this)
    private val twitter : TwitterClient = TwitterClient

    override fun onDestroy() {
        super.onDestroy()
        runFlag = false
    }

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_FETCH_TIMELINE -> {
                handleActionFetchTimeline()
            }
        }
    }

    private fun handleActionFetchTimeline() {
        runFlag = true
        while(runFlag) {
            try {
                try {
                    val timeline = twitter.getTimeline()
                    db.insertTimeLine(timeline, helper)
                } catch (e: ReadTimelineException) {
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                } finally {
                    Thread.sleep(DELAY)
                }
            } catch (e: InterruptedException) {
                runFlag = false
            }
        }
    }
}