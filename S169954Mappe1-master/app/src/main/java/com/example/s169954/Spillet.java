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
import android.widget.TableRow;
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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.ImageView;


public class Spillet extends Activity {

    Locale myLocale;
    private String[] ord;
    private Random rand;
    private String gjeldendeOrd;
    private String nyOrd;
    private LinearLayout ordLayout;
    private TextView[] charViews;

    private TableRow bokstaver1, bokstaver2, bokstaver3;
    private BokstavAdapter bokstavAdapter;

    private ImageView[] hengmannDeler;
    private int antallDeler = 7;
    private int gjeldendeDel;
    private int antallChars;
    private int antallKorrekt;

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

        hengmannDeler = new ImageView[antallDeler];
        hengmannDeler[0] = (ImageView)findViewById(R.id.galgen);
        hengmannDeler[1] = (ImageView)findViewById(R.id.hode);
        hengmannDeler[2] = (ImageView)findViewById(R.id.kroppen);
        hengmannDeler[3] = (ImageView)findViewById(R.id.arm1);
        hengmannDeler[4] = (ImageView)findViewById(R.id.arm2);
        hengmannDeler[5] = (ImageView)findViewById(R.id.bein1);
        hengmannDeler[6] = (ImageView)findViewById(R.id.bein2);

        bokstaver1 = (TableRow)findViewById(R.id.bokstaver1);
        bokstaver2 = (TableRow)findViewById(R.id.bokstaver2);
        bokstaver3 = (TableRow)findViewById(R.id.bokstaver3);

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

        gjeldendeDel = 0;
        antallChars = gjeldendeOrd.length();
        antallKorrekt = 0;

        for(int i = 0; i < antallDeler; i++) {
            hengmannDeler[i].setVisibility(View.INVISIBLE);
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

    public void bokstavTrykket(View view) {
        String bokstav = ((TextView)view).getText().toString();
        char bokstavChar = bokstav.charAt(0);
        view.setEnabled(false);
        view.setBackgroundColor(0xFF000000);

        boolean riktig = false;
        for (int i = 0; i < gjeldendeOrd.length(); i++) {
            if(gjeldendeOrd.charAt(i)==bokstavChar) {
                riktig = true;
                antallKorrekt++;
                charViews[i].setTextColor(Color.BLACK);
            }
        }

        if(riktig) {
            if(antallKorrekt == antallChars) {
                fjernKnapper();

                AlertDialog.Builder vunnetBuild = new AlertDialog.Builder(this);
                vunnetBuild.setTitle("YESS!");
                vunnetBuild.setMessage("Du har vunnet!\nRiktig svar var:\n" + gjeldendeOrd);
                vunnetBuild.setPositiveButton("Spill igjen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Spillet.this.playGame();
                    }
                });

                vunnetBuild.setNegativeButton("Avslutt", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Spillet.this.finish();
                    }
                });

                vunnetBuild.show();
            }
        }
        else if(gjeldendeDel < antallDeler) {
            hengmannDeler[gjeldendeDel].setVisibility(View.VISIBLE);
            gjeldendeDel++;
        }
        else {
            fjernKnapper();

            AlertDialog.Builder taptBuild = new AlertDialog.Builder(this);
            taptBuild.setTitle("OH!");
            taptBuild.setMessage("Du har tapt!\nRiktig svar var\n" + gjeldendeOrd);
            taptBuild.setPositiveButton("Spill igjen", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Spillet.this.playGame();
                }
            });

            taptBuild.setNegativeButton("Avslutte", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Spillet.this.finish();
                }
            });

            taptBuild.show();

        }
    }

    public void fjernKnapper() {
        int antallBokstaver = bokstaver1.getChildCount();
        antallBokstaver += bokstaver2.getChildCount();
        antallBokstaver += bokstaver3.getChildCount();

        for (int i = 0; i < antallBokstaver; i++) {
            bokstaver1.getChildAt(i).setEnabled(false);
            bokstaver2.getChildAt(i).setEnabled(false);
            bokstaver3.getChildAt(i).setEnabled(false);
        }

    }
}
