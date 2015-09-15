package com.example.s169954;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

public class Hoved extends Activity {

    Button reglerKnapp;
    Button spillKnapp;
    Button spraakKnapp;
    Button avsluttKnapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hovedmeny);
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

    public void addListenerOnButton() {
        reglerKnapp = (Button) findViewById(R.id.seregler);
        spillKnapp = (Button) findViewById(R.id.start_spill);
        spraakKnapp = (Button) findViewById(R.id.endre_spraak);
        avsluttKnapp = (Button) findViewById(R.id.avslutt);

        reglerKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Regler.class);
                startActivity(intent);
            }
        });
    }
}