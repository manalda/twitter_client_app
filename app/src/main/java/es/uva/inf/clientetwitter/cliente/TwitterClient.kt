package es.uva.inf.clientetwitter.cliente

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import es.uva.inf.clientetwitter.auxiliar.Constantes
import es.uva.inf.clientetwitter.auxiliar.ImpossibleToLoadAccountException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import twitter4j.Twitter
import twitter4j.TwitterException
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

object TwitterClient {
    private var twitter : Twitter? = null
    private var preferences : SharedPreferences? = null
    private var isAccountLoaded : Boolean = false

    fun loadAccount(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)

        val builder = ConfigurationBuilder()
        builder.setOAuthConsumerKey(readConsumerKey())
        builder.setOAuthConsumerSecret(readConsumerSecret())
        builder.setOAuthAccessToken(readAccessToken())
        builder.setOAuthAccessTokenSecret(readAccessTokenSecret())

        twitter =  TwitterFactory(builder.build()).instance
    }

    private fun readConsumerKey() : String {
        return preferences?.getString(Constantes.CONSUMER_KEY_TAG, null)
            ?: throw ImpossibleToLoadAccountException("consumer key == null")
    }

    private fun readConsumerSecret() : String {
        return preferences?.getString(Constantes.CONSUMER_SECRET_TAG, null)
            ?: throw ImpossibleToLoadAccountException("consumer secret == null")
    }

    private fun readAccessToken() : String {
        return preferences?.getString(Constantes.ACCESS_TOKEN_TAG, null)
            ?: throw ImpossibleToLoadAccountException("access token == null")
    }

    private fun readAccessTokenSecret() : String {
        return preferences?.getString(Constantes.ACCESS_TOKEN_SECRET_TAG, null)
            ?: throw ImpossibleToLoadAccountException("access token secret == null")
    }

    suspend fun sendTweet(status : String) : Int {
        return withContext(Dispatchers.Default) {
            try {
                this@TwitterClient.twitter!!.updateStatus(status)
                0
            } catch (e: TwitterException) {
                e.errorCode
            }
        }
    }

    fun isAccountLoaded() : Boolean {
        return isAccountLoaded
    }
}