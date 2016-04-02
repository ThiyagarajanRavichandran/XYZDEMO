package com.xyz.com.xyzresturent;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import com.xyz.com.xyzresturent.com.xyz.common.Common_Method;
import com.xyz.com.xyzresturent.com.xyz.common.Log_Tost;
import com.xyz.com.xyzresturent.com.xyz.common.Menu_Item;
import com.xyz.com.xyzresturent.com.xyz.common.StoreItems;
import com.xyz.com.xyzresturent.com.xyz.common.SummaryPojoClass;

/**
 * Created by THIYAGARAJAN-PC on 24/03/2016.
 */
public class Tab_Activity extends TabActivity {
    private TextView tvOrderNo,tvCovers,tvTableNo=null;
    private Context context=null;
    private Button bnConforOrder,bnClearData=null;
    private TabHost tabHost;
    private Bundle bundle=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabhost);
        context=Tab_Activity.this;

        tvOrderNo=(TextView)findViewById(R.id.OrderNo);
        tvCovers=(TextView)findViewById(R.id.tvCoverNo);
        tvTableNo=(TextView)findViewById(R.id.tvTableNo);
        bnConforOrder=(Button)findViewById(R.id.bnConformOrder) ;
        bnClearData=(Button)findViewById(R.id.bnClearData);
         tabHost=getTabHost();

        tabHost.addTab(tabHost.newTabSpec("Tab1").setIndicator("Menu Items")
                .setContent(new Intent().setClass(context,Menu_Item.class))
                );
        tabHost.addTab(tabHost.newTabSpec("Tab2").setIndicator("Summary")
                .setContent(new Intent().setClass(context,Summary.class))
        );
bnClearData.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        StoreItems.clearArrayLlist();
        tabHost.setCurrentTab(0);

    }
});
bnConforOrder.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(bnConforOrder.getText().toString().equals("Conform Order"))
        {

        }
        else
        {
            Log_Tost.Log_v("Item size", SummaryPojoClass.getAlItemName().size()+"");
            Common_Method.dataBaseAdapter.UpdateTable(tvTableNo.getText().toString(),"0");
            Intent intent=new Intent(context,BillRequest.class);
            Bundle bundle=new Bundle();
            bundle.putString("sTableNo",tvTableNo.getText().toString());
            bundle.putString("vcOrderId",tvOrderNo.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
});




        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener()
        {
            @Override
            public void onTabChanged(String tabId) {
                int itab=tabHost.getCurrentTab();
                if(itab==0)
                {
                    bnConforOrder.setText("Conform Order");
                }
                else
                {
                    bnConforOrder.setText("Bill Request");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bundle=this.getIntent().getExtras();
        tvOrderNo.setText(bundle.getString("vcOrderId"));
        tvCovers.setText(bundle.getString("Covers"));
        tvTableNo.setText(bundle.getString("sTableNo"));

    }


}
