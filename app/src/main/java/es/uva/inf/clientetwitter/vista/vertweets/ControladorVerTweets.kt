package es.uva.inf.clientetwitter.vista.vertweets

import android.database.Cursor
import es.uva.inf.clientetwitter.persistencia.DBConnection
import es.uva.inf.clientetwitter.persistencia.DBHelper

class ControladorVerTweets(private val vista : VerTweetsActivity) {
    private val db = DBConnection
    private val helper = DBHelper(vista)

    fun getTimeline() : Cursor? {
        return db.readTimeLine(helper)
    }

    fun deleteTimeline() {
        db.deleteTimeline(helper)
        vista.reloadTimeline()
    }
}