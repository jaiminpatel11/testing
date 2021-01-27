package com.goole.recycleselect_delete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    imageadapter imageadapter1;
    private int[] arr = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    String[] name;
    ArrayList<Image> imagelist;
    ArrayList<Image> selctionlist;
    int counter =0;


    boolean isContexualModeEnabled = false;
    TextView itemcounter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        name = getResources().getStringArray(R.array.image_name);
        imagelist = new ArrayList<>();
        selctionlist = new ArrayList<>();





        itemcounter = findViewById(R.id.item_count);
        itemcounter.setText("my app");

        for (int i = 0; i < name.length; i++) {
            Image image = new Image(arr[i], name[i]); imagelist.add(image);
        }


        recyclerView = findViewById(R.id.Recycleview);
        imageadapter1 = new imageadapter(imagelist, MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(imageadapter1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.normal_menu, menu);
        return true;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onLongClick(View view) {
        isContexualModeEnabled = true;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.contexual_menu);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setTitle("0 item selected");
        imageadapter1.notifyDataSetChanged();
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        return true;
    }

    public void Makeselection(View view, int adapterPosition) {
       if(((CheckBox)view).isChecked()){
           selctionlist.add(imagelist.get(adapterPosition));
           counter++;
           updatecounter();
       }
       else{
           selctionlist.remove(imagelist.get(adapterPosition));
           counter--;
           updatecounter();
       }

    }

  public void updatecounter(){

        itemcounter.setText(counter+"item selected");
  }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.delete){
            imageadapter1.Removeitem(selctionlist);

            Removecontexualactionmode();
        }
        return true;
    }

    private void Removecontexualactionmode() {
        isContexualModeEnabled=false;
        itemcounter.setText("my app");
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.normal_menu);
        counter=0;
        selctionlist.clear();
        imageadapter1.notifyDataSetChanged();
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }
}