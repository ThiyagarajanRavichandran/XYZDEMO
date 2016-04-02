package com.xyz.com.xyzresturent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminLogin extends Activity implements View.OnClickListener {

    private Button bnAddNewItem, bnBill = null;
    private Intent intent=null;
    private Context context=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        bnAddNewItem = (Button) findViewById(R.id.bnAddItem);
        bnBill = (Button) findViewById(R.id.bnBill);
        bnBill.setOnClickListener(this);
        bnAddNewItem.setOnClickListener(this);
        context=AdminLogin.this;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bnAddItem:
                intent=new Intent(context,AddItem.class);
                break;
            case R.id.bnBill:
                intent=new Intent(context,BillRequest.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
