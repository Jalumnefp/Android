package es.jfp.myapplication.navigation

import android.content.Context
import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import es.jfp.myapplication.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val prefs = activity?.getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE)

        val usernamePreference = findPreference<Preference>("USERNAME_LOGGED")
        val storedUsername = prefs?.getString("USERNAME_LOGGED", null)

        usernamePreference?.summary = storedUsername


    }
}