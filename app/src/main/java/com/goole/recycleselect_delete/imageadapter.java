package com.goole.recycleselect_delete;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class imageadapter extends RecyclerView.Adapter<imageadapter.Myviewholder> {
    ArrayList<Image> list = new ArrayList<>();
    MainActivity mainActivity;


    public imageadapter(ArrayList<Image> list, MainActivity mainActivity) {
        this.list = list;
        this.mainActivity = mainActivity;
    }




    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout, parent, false);
        Myviewholder myviewholder = new Myviewholder(v, mainActivity);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImageId());
        holder.textView.setText(list.get(position).getImageName());
        if (!mainActivity.isContexualModeEnabled) {
            holder.checkBox.setVisibility(View.GONE);
        } else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        CheckBox checkBox;
        View view;


        public Myviewholder(@NonNull View itemView, MainActivity mainActivity) {
            super(itemView);
            imageView = itemView.findViewById(R.id.Imageview);
            textView = itemView.findViewById(R.id.set_textview);
            checkBox = itemView.findViewById(R.id.set_checkbox);
            view = itemView;
            view.setOnLongClickListener(mainActivity);
            checkBox.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            mainActivity.Makeselection(view, getAdapterPosition());
        }


    }
    public void Removeitem(ArrayList<Image> selctionlist) {

        for (int i = 0; i < selctionlist.size(); i++) {
            list.remove(selctionlist.get(i));
            notifyDataSetChanged();


        }

    }

}








