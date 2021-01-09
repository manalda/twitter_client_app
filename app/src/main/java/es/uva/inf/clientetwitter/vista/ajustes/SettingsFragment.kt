package es.uva.inf.clientetwitter.vista.ajustes

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import es.uva.inf.clientetwitter.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

}

