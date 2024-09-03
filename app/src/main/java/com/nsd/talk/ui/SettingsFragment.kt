package com.nsd.talk.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.nsd.talk.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}