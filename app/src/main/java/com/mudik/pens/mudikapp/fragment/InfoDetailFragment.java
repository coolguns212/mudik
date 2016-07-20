package com.mudik.pens.mudikapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mudik.pens.mudikapp.R;
import com.mudik.pens.mudikapp.adapter.MediaAdapter;
import com.mudik.pens.mudikapp.model.Media;

import java.util.List;

/**
 * Created by Rimawanti Fauzyah on 14-Jul-16.
 */
public class InfoDetailFragment extends android.support.v4.app.Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vResult = inflater.inflate(R.layout.detail_info_fragment, container, false);


        return vResult;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView foto = (ImageView) view.findViewById(R.id.img_detailtempat);
        TextView kotaTxt = (TextView) view.findViewById(R.id.kota_detailtempat);
        TextView alamatTxt = (TextView) view.findViewById(R.id.alamat_detailtempat);
        TextView teleponTxt = (TextView) view.findViewById(R.id.telepon_detailtempat);
        TextView websiteTxt = (TextView) view.findViewById(R.id.website_detailtempat);
        TextView emailTxt = (TextView) view.findViewById(R.id.email_detailtempat);
        TextView fbTxt = (TextView) view.findViewById(R.id.fb_detailtempat);
        TextView latTxt = (TextView) view.findViewById(R.id.lat);
        TextView longTxt = (TextView) view.findViewById(R.id.log);
    }
}