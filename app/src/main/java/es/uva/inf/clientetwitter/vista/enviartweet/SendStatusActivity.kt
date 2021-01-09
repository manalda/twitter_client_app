package es.uva.inf.clientetwitter.vista.enviartweet

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import es.uva.inf.clientetwitter.R
import es.uva.inf.clientetwitter.auxiliar.Constantes.Companion.MAX_STATUS_CHARS
import es.uva.inf.clientetwitter.auxiliar.Constantes.Companion.MEDIUM_STATUS_CHARS
import es.uva.inf.clientetwitter.auxiliar.Constantes.Companion.MIN_STATUS_CHARS
import es.uva.inf.clientetwitter.cliente.TwitterClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SendStatusActivity : AppCompatActivity(), TextWatcher {
    private var controlador : ControladorSendStatus? = null
    private var statusField : EditText? = null
    private var charCount : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_status)

        controlador = ControladorSendStatus(this, TwitterClient)

        val sendTweet: Button = findViewById(R.id.buttonTweet)
        sendTweet.setOnClickListener {
            GlobalScope.launch {
                controlador!!.sendTweet()
            }
        }

        statusField = findViewById(R.id.editStatus)
        statusField!!.addTextChangedListener(this)

        charCount = findViewById(R.id.textCount)
    }

    fun getSendingStatus(): String {
        return statusField?.text.toString()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        val count = MAX_STATUS_CHARS - s!!.length
        val newCount = when {
            count == MIN_STATUS_CHARS -> {
                charCount?.setTextColor(Color.RED)
                count
            }
            count < MIN_STATUS_CHARS -> {
                s.delete(MAX_STATUS_CHARS, s.length)
                0
            }
            count < MEDIUM_STATUS_CHARS -> {
                charCount?.setTextColor(Color.YELLOW)
                count
            }
            else -> {
                charCount?.setTextColor(Color.GREEN)
                count
            }
        }

        charCount?.text = String.format("%s", newCount)
    }

    fun showResponseMessage(message : String) {
        Snackbar.make(statusField!!, message, Snackbar.LENGTH_LONG).show()
    }
}

