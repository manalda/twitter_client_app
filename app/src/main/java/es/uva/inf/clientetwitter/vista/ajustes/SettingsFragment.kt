package es.uva.inf.clientetwitter.vista.ajustes

import android.os.Bundle
import android.util.Log
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import es.uva.inf.clientetwitter.R
import es.uva.inf.clientetwitter.auxiliar.Constantes

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val accesstokensecret : EditTextPreference? = findPreference(Constantes.ACCESS_TOKEN_SECRET_TAG)
        accesstokensecret?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                Log.d("Settingsfragment", "AccessTokenSecret changed: $newValue")
                true
            }
    }

}

