package com.xyz.com.xyzresturent.com.xyz.common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.xyz.com.xyzresturent.R;

import java.util.ArrayList;

public class Menu_Item extends Activity {
    private Context context;
    private Common_Method common_method;
    private String sAllItem=null;
    private ArrayList<String> alItemId,alItemName=null;
    private ArrayList<Integer>alItemPrice,alItemQTY=null;
    private ItemAdapter itemAdapter=null;
    private ListView lvItemView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuitem);
        context=Menu_Item.this;
        common_method=new Common_Method();
        lvItemView=(ListView)findViewById(R.id.lvitemview);
        alItemId=new ArrayList<>();
        alItemName=new ArrayList<>();
        alItemPrice=alItemQTY=new ArrayList<>();

    }

    @Override
    protected void onResume() {
        super.onResume();
         sAllItem = Common_Method.dataBaseAdapter.getAllItem(alItemName,alItemId,alItemPrice);
        itemAdapter=new ItemAdapter(context,alItemId,alItemName,alItemPrice);
        lvItemView.setAdapter(itemAdapter);
    }
}
