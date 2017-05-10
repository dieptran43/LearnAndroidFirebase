package com.androidfirebase.diep.learnandroidfirebase;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 5/10/2017.
 */

public class AdapterUser extends ArrayAdapter<User> {
    Context context;
    int resource;
    List<User> objects;

    public AdapterUser(@NonNull Context context, @LayoutRes int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, parent, false);

//        TextView txtHoten = (TextView) view.findViewById(R.id.txtHoTen);
//        TextView txtTuoi = (TextView) view.findViewById(R.id.txtTuoi);
//        User user = objects.get(position);
//
//        txtHoten.setText(user.getHoten());
//        txtTuoi.setText(user.getTuoi().toString());
        return view;
    }
}