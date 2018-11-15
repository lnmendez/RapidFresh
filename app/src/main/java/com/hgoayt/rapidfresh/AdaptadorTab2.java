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

import com.hgoayt.rapidfresh.modelo.Contacto;

import java.util.List;

/**
 * Created by luis on 20-12-17.
 */

public class AdaptadorTab2 extends ArrayAdapter<Contacto> {

    public AdaptadorTab2(Context context, List<Contacto> lista) {
        super(context, 0, lista);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.itemlist_circle);
        TextView textView1 = (TextView) convertView.findViewById(R.id.itemlist_txt1);
        TextView textView2 = (TextView) convertView.findViewById(R.id.itemlist_txt2);
        TextView textView3 = (TextView) convertView.findViewById(R.id.itemlist_txt3);

        Contacto contacto = getItem(position);

        try {
            textView.setText(String.valueOf(contacto.getNombre_contacto().charAt(0)).toUpperCase());
            textView1.setText(contacto.getNombre_contacto());
            textView2.setText(contacto.getTelefono());
            textView3.setText("");
        } catch (Exception e) {
        }


        return convertView;

    }
}