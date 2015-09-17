package com.example.s169954;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Spillet extends Activity {

    Locale myLocale;
    private String[] ord;
    private Random rand;
    private String gjeldendeOrd;
    private String nyOrd;
    private LinearLayout ordLayout;
    private TextView[] charViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startspillet);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Resources res = getResources();
        ord = res.getStringArray(R.array.wordArray);

        rand = new Random();
        gjeldendeOrd = "";

        ordLayout = (LinearLayout)findViewById(R.id.ordet);

        playGame();
    }

    private void playGame() {
        for(int i = 0; i < ord.length; i++) {
            rand.nextInt(ord.length);
        }

        nyOrd = ord[rand.nextInt(ord.length)];
        while(nyOrd.equals(gjeldendeOrd)) nyOrd = ord[rand.nextInt(ord.length)];
        gjeldendeOrd = nyOrd;

        charViews = new TextView[gjeldendeOrd.length()];
        ordLayout.removeAllViews();

        for(int c = 0; c < gjeldendeOrd.length(); c++) {
            charViews[c] = new TextView(this);
            charViews[c].setText(""+gjeldendeOrd.charAt(c));

            charViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            charViews[c].setGravity(Gravity.CENTER);
            charViews[c].setTextColor(Color.WHITE);
            charViews[c].setBackgroundResource(R.drawable.bokstav_bg);

            ordLayout.addView(charViews[c]);
        }
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
