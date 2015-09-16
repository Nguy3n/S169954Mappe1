package com.example.s169954;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Locale;

public class Regler extends Activity {

    Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seregler);
        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Regler.class);
        startActivity(refresh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_regler, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.spraak:
                Toast.makeText(getApplicationContext(),"Trykket Språk", Toast.LENGTH_SHORT).show();
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

