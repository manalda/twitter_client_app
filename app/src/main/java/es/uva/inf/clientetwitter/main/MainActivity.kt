package es.uva.inf.clientetwitter.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import es.uva.inf.clientetwitter.R
import es.uva.inf.clientetwitter.vista.PanelActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val init: Button = findViewById(R.id.loadApp)
        init.setOnClickListener {
            startActivity(Intent(this, PanelActivity::class.java))
        }
    }
}