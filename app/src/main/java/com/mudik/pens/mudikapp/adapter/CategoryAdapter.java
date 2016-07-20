package com.mudik.pens.mudikapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mudik.pens.mudikapp.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Rimawanti Fauzyah on 7/9/2016.
 */
public class CategoryAdapter  extends ArrayAdapter<String> {

    private Context ctx;
    private String[] contentArray;
    private Integer[] imageArray;

    public CategoryAdapter(Context context, int resource, String[] objects,
                          Integer[] imageArray) {
        super(context,  R.layout.category_list_view, R.id.catlist_label_nama, objects);
        this.ctx = context;
        this.contentArray = objects;
        this.imageArray = imageArray;
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.category_list_view, parent, false);

        TextView textView = (TextView) row.findViewById(R.id.catlist_label_nama);
        textView.setText(contentArray[position]);

//        ImageView imageView = (ImageView)row.findViewById(R.id.catlist_img_icon);
//        int color = Color.parseColor("#009688"); //The color u want
//        imageView.setColorFilter(color);
//        imageView.setImageResource(imageArray[position]);

        return row;
    }
}