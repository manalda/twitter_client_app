import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import es.uva.inf.clientetwitter.auxiliar.Constantes

class DBHelper(context: Context?) :SQLiteOpenHelper(
    context,
    Constantes.DB_NAME,
    null,
    Constantes.DB_VERSION
) {
    private companion object {
        const val CREATE_TABLE = "create table ${Constantes.TABLA_TWEETS}(" +
                "${Constantes.ID_TWEET_TAG} int primary key," +
                "${Constantes.USUARIO_TWEET_TAG} text," +
                "${Constantes.MESSAGE_TWEET_TAG} text," +
                "${Constantes.CREATED_AT_TWEET_TAG} int)"
        const val DELETE_TABLE = "drop table if exists ${Constantes.TABLA_TWEETS}"
    }

    // Se llama para crear la tabla
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    // Se llama siempre que se tenga una nueva version
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DELETE_TABLE)
        onCreate(db)
    }

    fun insertTimeline() {

    }
}
