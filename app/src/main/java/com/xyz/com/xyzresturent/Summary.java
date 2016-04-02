package com.xyz.com.xyzresturent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.xyz.com.xyzresturent.com.xyz.common.StoreItems;
import com.xyz.com.xyzresturent.com.xyz.common.SummayAdapter;

/**
 * Created by THIYAGARAJAN-PC on 24/03/2016.
 */
public class Summary extends Activity  {
    private Context context=null;
    private ListView listView=null;
    private SummayAdapter summayAdapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        context=Summary.this;
        listView=(ListView)findViewById(R.id.lvsummaryItems);
    }

    @Override
    protected void onResume() {
        super.onResume();
        summayAdapter=new SummayAdapter(context, StoreItems.alItemName_temp,StoreItems.alItemPrice_temp,
                StoreItems.alItemQTY_temp);
        listView.setAdapter(summayAdapter);
    }


}
