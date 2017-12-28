package com.example.hushcoolcat.inanutshell;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Debbie on 2017-12-27.
 */

public class MyArrayAdapter<CharSequence> extends ArrayAdapter {

    public MyArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }




    public TextView getView(int position, View convertView, ViewGroup parent) {
        TextView v = (TextView) super.getView(position, convertView, parent);
        Typeface face = Typeface.create("serif-monospace", Typeface.NORMAL);
        v.setTypeface(face);
        return v;
    }

    public TextView getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView v = (TextView) super.getView(position, convertView, parent);
        Typeface face = Typeface.create("serif-monospace", Typeface.NORMAL);

        v.setTypeface(face);
        return v;
    }

}
