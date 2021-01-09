package es.uva.inf.clientetwitter.persistencia

import DBHelper
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import es.uva.inf.clientetwitter.auxiliar.Constantes
import twitter4j.Status

object DBConnection {

    fun insertTimeLine(tweets : List<Status>, helper: DBHelper) {
        val db = helper.writableDatabase
        val values = ContentValues()

        for(status in tweets) {
            values.clear()

            values.put(Constantes.ID_TWEET_TAG, status.id)
            values.put(Constantes.USUARIO_TWEET_TAG, status.user.name)
            values.put(Constantes.MESSAGE_TWEET_TAG, status.text)
            values.put(Constantes.CREATED_AT_TWEET_TAG, status.createdAt.time)

            db.insertWithOnConflict(Constantes.TABLA_TWEETS, null, values, SQLiteDatabase.CONFLICT_IGNORE)
        }

        db.close()
    }
}