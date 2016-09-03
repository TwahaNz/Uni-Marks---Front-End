package app.tnz.com.unimarks.activities;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import app.tnz.com.unimarks.R;


/**
 * Created by Admin on 2016/04/24.
 */
public class MyAdapter extends ArrayAdapter<String> {

    public MyAdapter(Context context, String[] values) {
        super(context, R.layout.custom_layout, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(super.getContext());

        View view = inflater.inflate(R.layout.custom_layout, parent, false);

        String courseName = getItem(position);

        TextView courseNames = (TextView) view.findViewById(R.id.courseName);

        if (position == 0)
            courseNames.setBackgroundColor(Color.parseColor("#0099FF"));
        if (position == 1)
            courseNames.setBackgroundColor(Color.parseColor("#003e6b"));
        if (position == 2)
            courseNames.setBackgroundColor(Color.parseColor("#ff6600"));
        if (position == 3)
            courseNames.setBackgroundColor(Color.parseColor("#009900"));
        if (position == 4)
            courseNames.setBackgroundColor(Color.parseColor("#993399"));
        /*if (position == 5)
            courseNames.setBackgroundColor(Color.parseColor("#cc0000"));*/

        courseNames.setText(courseName);

        return view;
    }
}