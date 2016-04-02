package com.xyz.com.xyzresturent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.xyz.com.xyzresturent.com.xyz.common.BillAdapter;
import com.xyz.com.xyzresturent.com.xyz.common.StoreItems;

/**
 * Created by THIYAGARAJAN-PC on 26/03/2016.
 */
public class BillRequest extends Activity {
    private Context context;
   private TextView tvTableNo,tvBillNo,tvTotal=null;
    private ListView lvBillItem=null;
    private BillAdapter billAdapter;
    private int iPrice=0;
    private Bundle bundle;
    private Button bnBillPrint=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill);
        context=BillRequest.this;
        tvTableNo=(TextView)findViewById(R.id.tvTableNo);
        tvBillNo=(TextView)findViewById(R.id.tvBillNo);
        tvTotal=(TextView)findViewById(R.id.tvTotal);
        lvBillItem=(ListView)findViewById(R.id.lvBillItem);
        bnBillPrint=(Button)findViewById(R.id.bnPrint);
        for(int iValue:StoreItems.alItemPrice_temp)
        {
                if(iPrice==0)
                {
                    iPrice=iValue;
                }
            else
                {
                    iPrice=iPrice+iValue;
                }
        }
        bnBillPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,CustomerLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bundle=this.getIntent().getExtras();
        tvBillNo.setText("Table No :  "+bundle.getString("vcOrderId"));
        tvTableNo.setText("Bill No     : "+bundle.getString("sTableNo"));
        tvTotal.setText("Total =" +Integer.toString(iPrice));
        billAdapter=new BillAdapter(context, StoreItems.alItemName_temp,StoreItems.alItemPrice_temp,StoreItems.alItemQTY_temp);
        lvBillItem.setAdapter(billAdapter);
    }
}
