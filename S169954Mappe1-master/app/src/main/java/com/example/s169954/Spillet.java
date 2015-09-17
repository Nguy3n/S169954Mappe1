package com.example.s169954;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import java.io.*;
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

    private ImageView[] hengmannDeler;
    private int antallDeler = 7;
    private int gjeldendeDel;
    private int antallChars;
    private int antallKorrekt;

    int vunnet = 0;
    int tapt = 0;
    String gjettet;
    TextView vunnetTv;
    TextView taptTv;
    TextView gjettetTv;

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


        /*Button knappQ = (Button)findViewById(R.id.knappQ);
        Button knappW = (Button)findViewById(R.id.knappW);
        Button knappE = (Button)findViewById(R.id.knappE);
        Button knappR = (Button)findViewById(R.id.knappR);
        Button knappT = (Button)findViewById(R.id.knappT);
        Button knappY = (Button)findViewById(R.id.knappY);
        Button knappU = (Button)findViewById(R.id.knappU);
        Button knappI = (Button)findViewById(R.id.knappI);
        Button knappO = (Button)findViewById(R.id.knappO);
        Button knappP = (Button)findViewById(R.id.knappP);
        Button knappAA = (Button)findViewById(R.id.knappAA);
        Button knappA = (Button)findViewById(R.id.knappA);
        Button knappS = (Button)findViewById(R.id.knappS);
        Button knappD = (Button)findViewById(R.id.knappD);
        Button knappF = (Button)findViewById(R.id.knappF);
        Button knappG = (Button)findViewById(R.id.knappG);
        Button knappH = (Button)findViewById(R.id.knappH);
        Button knappJ = (Button)findViewById(R.id.knappJ);
        Button knappK = (Button)findViewById(R.id.knappK);
        Button knappL = (Button)findViewById(R.id.knappL);
        Button knappOE = (Button)findViewById(R.id.knappOE);
        Button knappAE = (Button)findViewById(R.id.knappAE);
        Button knappZ = (Button)findViewById(R.id.knappZ);
        Button knappX = (Button)findViewById(R.id.knappX);
        Button knappC = (Button)findViewById(R.id.knappC);
        Button knappV = (Button)findViewById(R.id.knappV);
        Button knappB = (Button)findViewById(R.id.knappB);
        Button knappN = (Button)findViewById(R.id.knappN);
        Button knappM = (Button)findViewById(R.id.knappM);*/

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

        for(int j = 0; j < antallDeler; j++) {
            hengmannDeler[j].setVisibility(View.INVISIBLE);
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
        vunnetTv= (TextView)findViewById(R.id.vunnet);
        taptTv= (TextView)findViewById(R.id.tapt);
        gjettetTv= (TextView)findViewById(R.id.gjettet);

        String bokstav = ((TextView)view).getText().toString();
        char bokstavChar = Character.toLowerCase(bokstav.charAt(0));

        gjettet += bokstavChar;

        view.setEnabled(false);
        view.setBackgroundColor(0xFF000000);

        boolean riktig = false;
        for (int i = 0; i < gjeldendeOrd.length(); i++) {
            if(gjeldendeOrd.charAt(i)==bokstavChar) {
                riktig = true;
                antallKorrekt++;
                charViews[i].setTextColor(Color.RED);
            }
        }

        if(riktig) {
            if(antallKorrekt == antallChars) {
                restart();
                AlertDialog.Builder vunnetBuild = new AlertDialog.Builder(this);
                vunnetBuild.setTitle("YESS!");
                vunnetBuild.setMessage("Du har vunnet!\nRiktig svar var: " + gjeldendeOrd + "!");
                vunnetBuild.setPositiveButton("Spill igjen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Spillet.this.playGame();
                        restart();

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
            AlertDialog.Builder taptBuild = new AlertDialog.Builder(this);
            taptBuild.setTitle("OH!");
            taptBuild.setMessage("Du har tapt!\nRiktig svar var " + gjeldendeOrd + "!");
            taptBuild.setPositiveButton("Spill igjen", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Spillet.this.playGame();
                    restart();
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

    public void restart() {
        Intent intent = getIntent();
        //finish();
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void setVunnet(int i) {
        vunnet = i;
    }

    public void setTapt(int i) {
        tapt = i;
    }

    public void setGjettet(char c) {
        gjettet += c;
        gjettet.toUpperCase();
    }

    public int getVunnet() {
        return vunnet;
    }

    public int getTapt() {
        return tapt;
    }

    public String getGjettet() {
        return gjettet;
    }
}
