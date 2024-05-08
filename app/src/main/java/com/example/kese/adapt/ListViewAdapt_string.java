package com.example.kese.adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kese.Model.ListModel;
import com.example.kese.R;

import java.util.ArrayList;

public class ListViewAdapt_string extends ArrayAdapter<ListModel> {

    private  Context mContext;
    private int mResource;

    public ListViewAdapt_string(@NonNull Context context, int resource, @NonNull ArrayList<ListModel> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(this.mContext).inflate(this.mResource,parent,false);
        TextView name =convertView.findViewById(R.id.list_item_select_name);
        //ImageView photo =convertView.findViewById(R.id.list_item_my_photo);

        name.setText(getItem(position).getName());
        //photo.setImageResource(getItem(position).getPhoto());
        return convertView;
    }
}
