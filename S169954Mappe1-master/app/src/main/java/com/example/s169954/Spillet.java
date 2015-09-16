package com.example.s169954;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.Locale;

public class Spillet extends Activity {

    Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startspillet);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Spillet.class);
        startActivity(refresh);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_spillet, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tips:
                Toast.makeText(getApplicationContext(), this.getString(R.string.tips), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.norsk:
                Toast.makeText(getApplicationContext(),
                        "Du har forandret språket til Norsk!", Toast.LENGTH_SHORT)
                        .show();
                setLocale("nb");
                return true;
            case R.id.engelsk:
                Toast.makeText(getApplicationContext(),
                        "You have changed the language to English!", Toast.LENGTH_SHORT)
                        .show();
                setLocale("en");
                return true;
            case R.id.tysk:
                Toast.makeText(getApplicationContext(),
                        "Sie haben die Sprache auf Deutsch ändern!", Toast.LENGTH_SHORT)
                        .show();
                setLocale("de");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
