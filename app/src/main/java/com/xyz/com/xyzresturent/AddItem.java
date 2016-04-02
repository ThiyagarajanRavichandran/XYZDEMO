package com.xyz.com.xyzresturent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.xyz.com.xyzresturent.com.xyz.common.Common_Method;
import com.xyz.com.xyzresturent.com.xyz.common.RvItemViewAdapter;

import java.util.ArrayList;

/**
 * Created by THIYAGARAJAN-PC on 26/03/2016.
 */
public class AddItem extends Activity {
    private RecyclerView recyclerView = null;
    private RecyclerView.Adapter adapter = null;
    private RecyclerView.LayoutManager layoutManager = null;
    private ArrayList<String> alItemId, alItemName = null;
    private ArrayList<Integer> alItemPrice, alItemQTY = null;
    private Context context = null;
    private Button bnAddNewItem = null;
    private Common_Method common_method = null;
    private String sAllItems = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);
        context = AddItem.this;
        common_method = new Common_Method();
        recyclerView = (RecyclerView) findViewById(R.id.rvItemView);
        bnAddNewItem = (Button) findViewById(R.id.bnAddNewItem);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        alItemId = new ArrayList<>();
        alItemName = new ArrayList<>();
        alItemPrice = alItemQTY = new ArrayList<>();

        bnAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddNewItem.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        sAllItems = Common_Method.dataBaseAdapter.getAllItem(alItemName, alItemId, alItemPrice);
        adapter = new RvItemViewAdapter(context, alItemId, alItemName, alItemPrice);
        recyclerView.setAdapter(adapter);

    }
}
