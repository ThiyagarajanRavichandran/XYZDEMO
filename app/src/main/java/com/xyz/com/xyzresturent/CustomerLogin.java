package com.xyz.com.xyzresturent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.xyz.com.xyzresturent.com.xyz.common.Common_Method;
import com.xyz.com.xyzresturent.com.xyz.common.DataBaseAdapter;
import com.xyz.com.xyzresturent.com.xyz.common.Log_Tost;

import java.util.ArrayList;

public class CustomerLogin extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener {

    private TextView tvOrderTakerName=null;
    private EditText edCovers=null;
    private GridView gdTableView=null;
    private Context context=null;
    private TableAdapter tableAdapter =null;
    private ArrayList<String>alTableNo=null;
    private ArrayList<Integer>alTableCovers,alTableBooked=null;
    private DataBaseAdapter dataBaseAdapter=null;
    private Common_Method common_method=null;
    private Button bnConformTable=null;
    private ArrayList<Integer> alBookedTableNo;
    private String sTableNo=null,sMsg=null,sTotalCovers=null,sOrderId=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        context=CustomerLogin.this;
        common_method=new Common_Method();
        findViewById();
        alTableNo=new ArrayList<>();
        alTableCovers=new ArrayList<>();
        alTableBooked=new ArrayList<>();


        gdTableView.setOnItemClickListener(this);

        bnConformTable.setOnClickListener(this);

    }

    private void findViewById() {

        tvOrderTakerName=(TextView)findViewById(R.id.tvCustomerName);
        edCovers=(EditText)findViewById(R.id.edCovers);
        gdTableView=(GridView)findViewById(R.id.gdTableView);
        bnConformTable=(Button)findViewById(R.id.bnConformTable);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Common_Method.dataBaseAdapter.getallTabledata(alTableNo,alTableCovers,alTableBooked);
       sOrderId= Common_Method.dataBaseAdapter.getOrderTable(context);
        tableAdapter =new TableAdapter(context,alTableNo,alTableCovers,alTableBooked);
        gdTableView.setAdapter(tableAdapter);
    }

    @Override
    public void onClick(View v) {
            String sedTotalCovers=edCovers.getText().toString();
        if((sTableNo!=null))
        {
            if((!sTotalCovers.equals(sedTotalCovers)) && (!sedTotalCovers.equals("0")))
            {
                Common_Method.dataBaseAdapter.UpdateTable(sTableNo,"1");
                Common_Method.dataBaseAdapter.insert_OrderTable(sOrderId,sTableNo);
                Intent intent=new Intent(context,Tab_Activity.class);
                Bundle bundle=new Bundle();
                bundle.putString("Covers",sedTotalCovers);
                bundle.putString("sTableNo",sTableNo);
                bundle.putString("vcOrderId",sOrderId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else
            {
                Log_Tost.Toast_Msg(context,"Fill atleast one cover/Only"+sTotalCovers+"allowed");
            }

        }
        else
        {
            Log_Tost.Toast_Msg(context,"Pls Select Table");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView textView=(TextView)view.findViewById(R.id.tvTableNo);

        if(alTableBooked.get(position)==1)
        {
            sMsg="Table is Booked";
        }
        else
        {
           sMsg= sTableNo=alTableNo.get(position);
            sTotalCovers=Integer.toString(alTableCovers.get(position));

        }


        Log_Tost.Toast_Msg(context,sMsg);


    }
}
