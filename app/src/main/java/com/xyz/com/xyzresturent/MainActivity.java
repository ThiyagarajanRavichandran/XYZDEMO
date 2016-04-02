package com.xyz.com.xyzresturent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xyz.com.xyzresturent.com.xyz.common.Common_Method;
import com.xyz.com.xyzresturent.com.xyz.common.DataBaseAdapter;
import com.xyz.com.xyzresturent.com.xyz.common.Log_Tost;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    private Context context=null;
    private Button bnAdmin,bnCustomer=null;
    private DataBaseAdapter dataBaseAdapter=null;
    private ArrayList<String>alTabelNo,alTableColumnNames=null,alSubCat=null,alItem=null;
    private Common_Method common_method=null;
    private String[] sA_TableNames=null,sA_ColumnName=null,sA_SubCat=null,sA_Item=null;
    private long id,id2,id3,id4,id5,id6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        common_method=new Common_Method();
        dataBaseAdapter=new DataBaseAdapter(context);
        alTabelNo=new ArrayList<>();
        alTableColumnNames=new ArrayList<>();
        alSubCat=new ArrayList<>();
        alItem=new ArrayList<>();
        findViewById();
        setonClickListener(this);
        Common_Method.dataBaseAdapter =dataBaseAdapter;

        alTabelNo.clear();
        alTableColumnNames.clear();
        alSubCat.clear();
        alItem.clear();
        int iCount=dataBaseAdapter.Data_Test();
        if(iCount!=0)
        {
            Log_Tost.Toast_Msg(context,"Table is Already Created");
        }
        else {
            sA_TableNames=common_method.getResourceStringArray(context,R.array.Table_Names);
            sA_ColumnName=common_method.getResourceStringArray(context,R.array.TableColumnNames);
            sA_SubCat=common_method.getResourceStringArray(context,R.array.SubCatName);
            sA_Item=common_method.getResourceStringArray(context,R.array.ItemName);
            for(int iValues=0;iValues< sA_TableNames.length;iValues++)
            {
                alTabelNo.add(sA_TableNames[iValues]);


            }
            for(int iValues=0;iValues< sA_ColumnName.length;iValues++)
            {

                alTableColumnNames.add(sA_ColumnName[iValues]);
                alSubCat.add(sA_SubCat[iValues]);
                alItem.add(sA_Item[iValues]);

            }
            id= dataBaseAdapter.insert_table(alTabelNo);
            id2=dataBaseAdapter.insert_Subcat(alSubCat);
            id3=dataBaseAdapter.insert_item(alItem);
            if((id<0)&&(id2<0)&&(id3<3))
            {
                Log_Tost.Toast_Msg(context,"Insert was Unsuccessfull");
            }
            else
            {
                Log_Tost.Toast_Msg(context,"Insert was successfull");
            }
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
            }

    /*Set Onclick for Controllers*/
    private void setonClickListener(MainActivity mainActivity) {
        bnAdmin.setOnClickListener(mainActivity);
        bnCustomer.setOnClickListener(mainActivity);
    }
/*Find the id from xml for the controllers*/
    private void findViewById() {
        bnAdmin=(Button)findViewById(R.id.bnAdmin);
        bnCustomer=(Button)findViewById(R.id.bnCustomer);

    }


    /*Onclick Method using interface*/
    @Override
    public void onClick(View v) {
        Intent intent=null;
/*Get the Id of button*/
        switch (v.getId())
        {
            case R.id.bnAdmin:
                intent = new Intent(context, AdminLogin.class);
                break;
            case R.id.bnCustomer:
                intent=new Intent(context,CustomerLogin.class);

                break;
        }
        if(intent!=null)
        {
            startActivity(intent);
        }
    }
}
