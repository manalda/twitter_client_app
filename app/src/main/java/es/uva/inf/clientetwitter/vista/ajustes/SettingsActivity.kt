package es.uva.inf.clientetwitter.vista.ajustes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.uva.inf.clientetwitter.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        if (savedInstanceState == null) {
            val fragment = SettingsFragment()

            val manager = supportFragmentManager
            manager.beginTransaction()
                .add(android.R.id.content, fragment, fragment::class.java.getSimpleName())
                .commit()
        }
    }
}