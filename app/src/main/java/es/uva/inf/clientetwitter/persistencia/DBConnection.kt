package es.uva.inf.clientetwitter.persistencia

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import es.uva.inf.clientetwitter.auxiliar.Constantes
import twitter4j.Status

object DBConnection {
    private const val READ_TIMELINE = "SELECT *  FROM ${Constantes.TABLA_TWEETS}"

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

    fun readTimeLine(helper: DBHelper) : Cursor? {
        val db = helper.readableDatabase
        return db.rawQuery(READ_TIMELINE, null)
    }
}