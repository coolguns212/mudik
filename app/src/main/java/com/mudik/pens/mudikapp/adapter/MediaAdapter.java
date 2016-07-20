package com.mudik.pens.mudikapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.mudik.pens.mudikapp.R;
import com.mudik.pens.mudikapp.model.Media;
import com.mudik.pens.mudikapp.model.Place;

import java.util.List;

/**
 * Created by Rimawanti Fauzyah on 7/11/2016.
 */
public class MediaAdapter extends ArrayAdapter<Media> {
    //private List<Place> placeList = null;
    private List<Media> mediaList = null;
    private Context context;
    // private PackageManager packageManager;

    public MediaAdapter(Context context, int textViewResourceId,
                        List<Media> mediaList) {
        super(context, textViewResourceId, mediaList);
        this.context = context;
        this.mediaList = mediaList;
        //  packageManager = context.getPackageManager();
    }



    @Override
    public int getCount() {
        return ((null != mediaList) ? mediaList.size() : 0);
    }

    @Override
    public Media getItem(int position) {
        return ((null != mediaList) ? mediaList.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.media_list_view, null);
        }

        Media data1 = mediaList.get(position);
        //Media data1 = mediaList.get(position);
        if (null != data1) {
            TextView mNama = (TextView) view.findViewById(R.id.medialist_label_nama);
            TextView mCat = (TextView) view.findViewById(R.id.medialist_label_kategori);
            TextView mid = (TextView) view.findViewById(R.id.media_id);

            mNama.setText(data1.m_name);
            mCat.setText(data1.m_cat);
            mid.setText(data1.m_konten);


        }

        return view;
    }
}
