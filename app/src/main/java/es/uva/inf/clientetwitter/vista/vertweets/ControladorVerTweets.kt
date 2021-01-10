package es.uva.inf.clientetwitter.vista.vertweets

import android.database.Cursor
import es.uva.inf.clientetwitter.persistencia.DBConnection
import es.uva.inf.clientetwitter.persistencia.DBHelper

class ControladorVerTweets(private val vista : VerTweetsActivity) {
    private val db = DBConnection

    fun getTimeline() : Cursor? {
        return db.readTimeLine(DBHelper(vista))
    }
}