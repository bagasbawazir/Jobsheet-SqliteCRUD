package id.ac.polinema.sqlite.fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import id.ac.polinema.sqlite.R;

public class SettingFragment extends PreferenceFragmentCompat {

	@Override
	public void onCreatePreferences(Bundle bundle, String s) {
		addPreferencesFromResource(R.xml.preference);
	}
}
