package app.di_v.messenger707.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import app.di_v.messenger707.R

class SettingFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}