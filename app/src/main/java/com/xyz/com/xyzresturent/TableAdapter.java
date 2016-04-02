package com.xyz.com.xyzresturent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by THIYAGARAJAN-PC on 3/13/2016.
 */
public class TableAdapter extends BaseAdapter{
    private Context context=null;
    private ArrayList<String>alTableNo=null;
    private ArrayList<Integer>alTableCovers,alTableBooked=null;
    public TableAdapter(Context context, ArrayList<String> alTableNo, ArrayList<Integer> alTableCovers,
                        ArrayList<Integer> alTableBooked) {
        this.context=context;
        this.alTableNo=alTableNo;
        this.alTableCovers=alTableCovers;
        this.alTableBooked=alTableBooked;
    }

    @Override
    public int getCount() {
        return alTableNo.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.tableview,null);
            viewHolder.tvTableNo=(TextView)convertView.findViewById(R.id.tvTableNo);
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.tvTableNo.setText(alTableNo.get(position));
        return convertView;
    }

public class ViewHolder{
    TextView tvTableNo=null;
}
}
