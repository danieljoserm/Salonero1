package main.salonero1;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import java.util.Locale;

/**
 * Created by danie on 7/7/2017.
 */

public class startpreferences extends Application {

    private Locale locale;
    private Configuration config = new Configuration();

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences prefs =   getSharedPreferences("mis preferencias", Context.MODE_PRIVATE);
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here

            String language=  locale.getDefault().getDisplayLanguage();

            // mark first time has runned.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.putString("language", language);
            editor.putInt("idName", 12);
            editor.apply();

            editor.commit();
        }

        //SharedPreferences prefs1 =  PreferenceManager.getDefaultSharedPreferences(this);
        String language = prefs.getString("language", "No name defined");//"No name defined" is the default value.

        locale = new Locale(language);
        config.locale =locale;
        getResources().updateConfiguration(config, null);



    }
}
