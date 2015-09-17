package com.example.s169954;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.view.View.OnClickListener;
import java.util.Locale;
import android.content.res.Resources;

public class Hoved extends Activity {

    Spinner spraakSpinner;
    Locale myLocale;
    Button spillKnapp;
    Button reglerKnapp;
    Button avsluttKnapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hovedmeny);

        ArrayAdapter myAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, new String[]{getString(R.string.endre_spraak), "Norsk", "English", "Deutsch"});
        spraakSpinner = (Spinner) findViewById(R.id.spraak_spinner);
        spraakSpinner.setAdapter(myAdapter);

        spraakSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 1) {
                    Toast.makeText(parent.getContext(),
                            "Du har forandret språket til Norsk!", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("nb");
                } else if (position == 2) {
                    Toast.makeText(parent.getContext(),
                            "You have changed the language to English!", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("en");
                } else if (position == 3) {
                    Toast.makeText(parent.getContext(),
                            "Sie haben die Sprache auf Deutsch ändern!", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("de");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO
            }
        });

        spillKnapp = (Button)findViewById(R.id.start_spill);
        reglerKnapp = (Button)findViewById(R.id.se_regler);
        avsluttKnapp = (Button)findViewById(R.id.avslutt);

        spillKnapp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Hoved.this, Spillet.class);
                startActivity(myIntent);
            }
        });

        reglerKnapp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Hoved.this, Regler.class);
                startActivity(myIntent);
            }
        });

        avsluttKnapp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                //System.exit(0);

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });
    }

    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Hoved.class);
        startActivity(refresh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.spraak:
                Toast.makeText(getApplicationContext(), "Trykket Språk", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}