package com.xyz.com.xyzresturent.com.xyz.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xyz.com.xyzresturent.R;

import java.util.ArrayList;

/**
 * Created by THIYAGARAJAN on 01-04-2016.
 */
public class BillAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> alItemName_temp=null;
    private ArrayList<Integer> alItemPrice_temp, alItemQTY_temp=null;
    public BillAdapter(Context context, ArrayList<String> alItemName_temp, ArrayList<Integer> alItemPrice_temp,
                       ArrayList<Integer> alItemQTY_temp) {
        this.context=context;
        this.alItemName_temp=alItemName_temp;
        this.alItemPrice_temp=alItemPrice_temp;
        this.alItemQTY_temp=alItemQTY_temp;
    }

    @Override
    public int getCount() {
        return alItemName_temp.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
public class MyHolder {
    private TextView tvSlno,tvItemName,tvItemQTY,tvItemPrice=null;
}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder;
        if(convertView==null)
        {
        myHolder=new MyHolder();
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.billitemview,null);
            myHolder.tvSlno=(TextView)convertView.findViewById(R.id.tvSlNo);
            myHolder.tvItemName=(TextView)convertView.findViewById(R.id.tvItemName);
            myHolder.tvItemQTY=(TextView)convertView.findViewById(R.id.tvItemOTY1);
            myHolder.tvItemPrice=(TextView)convertView.findViewById(R.id.tvItemPrice);
            convertView.setTag(myHolder);
        }
        else
        {
            myHolder=(MyHolder)convertView.getTag();
        }
        myHolder.tvSlno.setText(Integer.toString(position+1));
        myHolder.tvItemName.setText(alItemName_temp.get(position));
        myHolder.tvItemQTY.setText(Integer.toString(alItemQTY_temp.get(position)));
        myHolder.tvItemPrice.setText(Integer.toString(alItemPrice_temp.get(position)));

        return convertView;
    }
}
