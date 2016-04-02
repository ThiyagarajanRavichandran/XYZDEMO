package com.xyz.com.xyzresturent.com.xyz.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.xyz.com.xyzresturent.R;

import java.util.ArrayList;

import static com.xyz.com.xyzresturent.com.xyz.common.StoreItems.*;

/**
 * Created by THIYAGARAJAN-PC on 19/03/2016.
 */
public class ItemAdapter extends BaseAdapter{

    Context context;
    ArrayList<String>alItemId,alItemName=null;
    ArrayList<Integer>alItemPrice,alItemCount=null;
    public  ArrayList<String>alItemName_temp=new ArrayList<>();
    public  ArrayList<Integer>alItemPrice_temp=new ArrayList<>();
    public  ArrayList<Integer>alItemQTY_temp=new ArrayList<>();
    private MyHolder myholder=null;


    public ItemAdapter (Context context,ArrayList<String>alItemId,ArrayList<String>alItemName,ArrayList<Integer>alItemPrice)
{
this.context=context;
    this.alItemId=alItemId;
    this.alItemName=alItemName;
    this.alItemPrice=alItemPrice;
}


    @Override
    public int getCount()
    {
        return alItemId.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent)
    {


        if(convertView==null)
        {

            myholder=new MyHolder();
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.itemview,null);
            myholder.tvItemName=(TextView)convertView.findViewById(R.id.tvItemName);
            myholder.tvItemCount=(TextView)convertView.findViewById(R.id.tvCount);
            myholder.tvItemPrice=(TextView)convertView.findViewById(R.id.tvItemPrice);
            myholder.bnMinus=(Button)convertView.findViewById(R.id.bnMinus);
            myholder.bnPlus=(Button)convertView.findViewById(R.id.bnPlus);

            myholder.bnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyHolder myHolder1=(MyHolder)v.getTag();
                    myHolder1.iCount=Integer.parseInt(myHolder1.tvItemCount.getText().toString());
                    myHolder1.iCount++;
                String sCount=Integer.toString(myHolder1.iCount);


                Log_Tost.Log_v("Button Plus",sCount);
                    myHolder1.tvItemCount.setText(sCount);
                    if(StoreItems.alItemName_temp.contains(myHolder1.tvItemName.getText().toString()))
                    {
                        int price=Integer.parseInt(myHolder1.tvItemPrice.getText().toString());
                        int count=Integer.parseInt(myHolder1.tvItemCount.getText().toString());
                        int total=price*count;
                        int index=StoreItems.alItemName_temp.indexOf(myHolder1.tvItemName.getText().toString());
                        StoreItems.alItemQTY_temp.set(index,Integer.parseInt(myHolder1.tvItemCount.getText().toString()));
                        StoreItems.alItemPrice_temp.set(index,total);
                    }
                    else
                    {

                        StoreItems. alItemName_temp.add(myHolder1.tvItemName.getText().toString());
                        StoreItems.alItemQTY_temp.add(Integer.parseInt(myHolder1.tvItemCount.getText().toString()));
                        StoreItems. alItemPrice_temp.add(Integer.parseInt(myHolder1.tvItemPrice.getText().toString()));
                    }
                    Log_Tost.Log_v("Arraylist Size",StoreItems.alItemName_temp.size()+"-"+StoreItems.alItemQTY_temp.size()
                            +"-"+StoreItems.alItemPrice_temp.size());

                }
            });
            myholder.bnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyHolder myHolder1 = (MyHolder) v.getTag();
                    myHolder1.iCount = Integer.parseInt(myHolder1.tvItemCount.getText().toString());
                    int index=StoreItems.alItemName_temp.indexOf(myHolder1.tvItemName.getText().toString());
                    if(myHolder1.iCount==0)
                    {
                        Log_Tost.Toast_Msg(context,"Item QTY Can't set Zero");
                        StoreItems.alItemName_temp.remove(index);
                        StoreItems. alItemQTY_temp.remove(index);
                        StoreItems.alItemPrice_temp.remove(index);
                    }
                    else
                    {


                        myHolder1.iCount--;
                        String sCount = Integer.toString(myHolder1.iCount);
                        Log_Tost.Log_v("Button Minus", sCount);
                        myHolder1.tvItemCount.setText(sCount);
                        if(StoreItems.alItemName_temp.size()!=0)
                        {



                            if(StoreItems.alItemQTY_temp.get(index)!=0)
                            {
                                int price=Integer.parseInt(myHolder1.tvItemPrice.getText().toString());
                                int count=Integer.parseInt(myHolder1.tvItemCount.getText().toString());
                                int total=price*count;
                                StoreItems.alItemQTY_temp.set(index,Integer.parseInt(myHolder1.tvItemCount.getText().toString()));
                                StoreItems.alItemPrice_temp.set(index,total);
                                Log_Tost.Toast_Msg(context,"QTY NON ZERO");
                            }
                            else
                            {
                                StoreItems. alItemName_temp.remove(index);
                                StoreItems. alItemQTY_temp.remove(index);
                                StoreItems.alItemPrice_temp.remove(index);
                            }

                        }
                        Log_Tost.Log_v("Arraylist Size",StoreItems.alItemName_temp.size()+"-"+StoreItems.alItemQTY_temp.size()
                                +"-"+StoreItems.alItemPrice_temp.size());
                    }

                }
            });
            convertView.setTag(myholder);

        }
        else
        {
            myholder=(MyHolder)convertView.getTag();
        }
            myholder.bnPlus.setTag(myholder);
        myholder.bnMinus.setTag(myholder);
        myholder.tvItemName.setText(alItemName.get(position));

        myholder.tvItemPrice.setText(Integer.toString(alItemPrice.get(position)));

       // myholder.tvItemCount.setText(Integer.toString( myholder.iCount));


        return convertView;
    }

    public class MyHolder
    {

        TextView tvItemName,tvItemCount,tvItemPrice=null;
        Button bnPlus,bnMinus=null;
        int iCount=0;
    }
}
