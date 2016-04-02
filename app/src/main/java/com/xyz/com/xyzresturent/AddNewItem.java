package com.xyz.com.xyzresturent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xyz.com.xyzresturent.com.xyz.common.Common_Method;
import com.xyz.com.xyzresturent.com.xyz.common.Log_Tost;

import java.util.ArrayList;

public class AddNewItem extends AppCompatActivity {
    private EditText edItemId, edItemName, edItemprice = null;
    private Button bnSubmit = null;
    private Context context = null;
    private String sItemId = "", sItemName = "", sItemPrice = "";
    private ArrayList<String> alItem = null;
    private Common_Method common_method = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        context = AddNewItem.this;
        edItemId = (EditText) findViewById(R.id.edItemId);
        edItemName = (EditText) findViewById(R.id.edItemName);
        edItemprice = (EditText) findViewById(R.id.edItemPrice);
        bnSubmit = (Button) findViewById(R.id.bnSubmit);
        alItem = new ArrayList<>();
        common_method = new Common_Method();


    }

    @Override
    protected void onResume() {
        super.onResume();
        bnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alItem.clear();
                sItemId = edItemId.getText().toString();
                sItemName = edItemName.getText().toString();
                sItemPrice = edItemprice.getText().toString();
                if ((!sItemId.equals("")) && (!sItemName.equals("")) && (!sItemPrice.equals(""))) {
                    alItem.add(sItemId + "!" + sItemName + "@" + "SUB-1" + "#" + sItemPrice);
                    long id = Common_Method.dataBaseAdapter.insert_item(alItem);
                    if (id > 0) {
                        Log_Tost.Toast_Msg(context, "Insert was successfull");
                        Intent intent = new Intent(context, AddItem.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        Log_Tost.Toast_Msg(context, "Insert was Unsuccessfull");
                    }

                } else {
                    Log_Tost.Toast_Msg(context, "Please fill any one filed");
                }


            }
        });
    }
}
