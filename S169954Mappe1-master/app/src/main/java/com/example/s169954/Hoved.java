package com.example.s169954;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Hoved extends Activity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hovedmeny);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        final Context context = this;
        button = (Button)findViewById(R.id.seregler);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, Regler.class);
                startActivity(intent);
            }
        });
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

    public void onClickListener() {
        Button seregler = (Button)findViewById(R.id.seregler);
        seregler.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(Hoved.this, Regler.class);
                startActivity(intent);
            }
        });
    }


}
