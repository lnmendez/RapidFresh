package com.hgoayt.rapidfresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hgoayt.rapidfresh.modelo.Recepcion;

import java.util.List;

/**
 * Created by luis on 20-12-17.
 */

public class AdaptadorTab1 extends ArrayAdapter<Recepcion> {

    public AdaptadorTab1(Context context, List<Recepcion> lista) {
        super(context, 0, lista);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.itemlist_circle);
        TextView textView1 = (TextView) convertView.findViewById(R.id.itemlist_txt1);
        TextView textView2 = (TextView) convertView.findViewById(R.id.itemlist_txt2);
        TextView textView3 = (TextView) convertView.findViewById(R.id.itemlist_txt3);

        Recepcion recepcion = getItem(position);

        try {
            textView.setText(String.valueOf(recepcion.getNombre_producto().charAt(0)).toUpperCase());
            textView1.setText(recepcion.getNombre_producto());
            textView2.setText(recepcion.getProductor());
            textView3.setText(recepcion.getFecha_recepcion());
        } catch (Exception e) {
        }

        return convertView;
    }

}
