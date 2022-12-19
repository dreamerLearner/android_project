package com.txzing.spinnertest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.txzing.spinnertest.model.Hero;

import java.util.LinkedList;

public class MyAdapter extends BaseAdapter {


    private LinkedList<Hero> heroes;
    private Context context;

    public MyAdapter(LinkedList<Hero> heroes, Context context) {
        this.heroes = heroes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return heroes.size();
    }

    @Override
    public Object getItem(int i) {
        return heroes.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_layout_hero,viewGroup,false);
        Log.e("Tag:",heroes.get(i).getHname());
        ImageView imageview = view.findViewById(R.id.image);
        TextView textview = view.findViewById(R.id.textview);
        imageview.setImageResource(heroes.get(i).gethIcon());
        textview.setText(heroes.get(i).getHname());
        return view;
    }
}
