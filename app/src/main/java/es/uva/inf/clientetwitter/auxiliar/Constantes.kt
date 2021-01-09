package es.uva.inf.clientetwitter.auxiliar

import android.provider.BaseColumns

class Constantes {
    companion object {
        const val MAX_STATUS_CHARS = 280
        const val MIN_STATUS_CHARS = 0
        const val MEDIUM_STATUS_CHARS = 10
        const val ACCESS_TOKEN_TAG = "accesstoken"
        const val ACCESS_TOKEN_SECRET_TAG = "accesstokensecret"
        const val CONSUMER_KEY_TAG = "consumerkey"
        const val CONSUMER_SECRET_TAG = "consumersecret"
        const val DB_NAME = "tweets.db"
        const val DB_VERSION = 1
        const val TABLA_TWEETS = "tweets"
        const val ID_TWEET_TAG = BaseColumns._ID
        const val USUARIO_TWEET_TAG = "usuario"
        const val MESSAGE_TWEET_TAG = "mensaje"
        const val CREATED_AT_TWEET_TAG = "createdAt"
    }
}