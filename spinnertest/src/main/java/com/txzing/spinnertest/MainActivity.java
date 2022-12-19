package com.txzing.spinnertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.txzing.spinnertest.model.Hero;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private List<Hero> heroes;
    private boolean selected = false;
    private Spinner spinner;
    private Context context;
    private MyAdapter myAdapter;

    private void bindview(){


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        heroes = new LinkedList<>();
        spinner = findViewById(R.id.spinner);
        heroes.add(new Hero(R.mipmap.ic_launcher,"德玛西亚之力"));
        heroes.add(new Hero(R.mipmap.ic_launcher,"妖姬"));
        heroes.add(new Hero(R.mipmap.ic_launcher,"蛮子"));
        heroes.add(new Hero(R.mipmap.ic_launcher,"瞎子"));
        Log.e("Tag:",heroes.get(0).toString());
        myAdapter = new MyAdapter((LinkedList<Hero>) heroes,context);
        spinner.setAdapter(myAdapter);
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinner:
                if(selected){
                    Hero selectedItem = (Hero) parent.getSelectedItem();
                    Toast.makeText(context,"您的英雄是：" + selectedItem.getHname(),
                            Toast.LENGTH_SHORT).show();
                }else selected = true;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}