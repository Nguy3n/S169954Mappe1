package com.example.s169954;

import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;

import java.util.Objects;

public class BokstavAdapter extends BaseAdapter  {
    private String[] bokstaver;
    private LayoutInflater bokstavInf;

    public BokstavAdapter(Context c) {
        bokstaver = new String[29];
        for(int b = 0; b < bokstaver.length; b++) {
            bokstaver[b] = "" + (char)(b+'A');
        }

        bokstavInf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return bokstaver.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button bokstavBtn;
        if(convertView == null) {
            bokstavBtn = (Button)bokstavInf.inflate(R.layout.startspillet, parent, false);
        }
        else {
            bokstavBtn = (Button) convertView;
        }
        bokstavBtn.setText(bokstaver[position]);
        return bokstavBtn;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }
}
