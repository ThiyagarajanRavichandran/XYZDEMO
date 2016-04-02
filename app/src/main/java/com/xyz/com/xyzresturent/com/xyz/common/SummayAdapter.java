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

/**
 * Created by THIYAGARAJAN on 01-04-2016.
 */
public class SummayAdapter extends BaseAdapter{
    Context context;
    ArrayList<String> alItemName=null;
    ArrayList<Integer>alItemPrice,alItemCount=null;
    public static ArrayList<String>alItemName_temp=new ArrayList<>();
    public static ArrayList<Integer>alItemPrice_temp=new ArrayList<>();
    public static ArrayList<Integer>alItemQTY_temp=new ArrayList<>();
    private MyHolder myholder=null;
    private Integer[] price;


    public SummayAdapter (Context context,ArrayList<String>alItemName,ArrayList<Integer>alItemPrice,ArrayList<Integer>alItemCount)
    {
        this.context=context;
        this.alItemName=alItemName;
        this.alItemPrice=alItemPrice;
        this.alItemCount=alItemCount;
    }


    @Override
    public int getCount()
    {
        return alItemName.size();
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
            convertView=layoutInflater.inflate(R.layout.summaryitem,null);
            myholder.tvItemName=(TextView)convertView.findViewById(R.id.summarytvItemName);
            myholder.tvItemCount=(TextView)convertView.findViewById(R.id.summarytvCount);
            myholder.tvItemPrice=(TextView)convertView.findViewById(R.id.summarytvItemPrice);
            myholder.bnMinus=(Button)convertView.findViewById(R.id.summarybnMinus);
            myholder.bnPlus=(Button)convertView.findViewById(R.id.summarybnPlus);

            myholder.bnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyHolder myHolder1=(MyHolder)v.getTag();
                    myHolder1.iCount=Integer.parseInt(myHolder1.tvItemCount.getText().toString());
                    myHolder1.iCount++;
                    String sCount=Integer.toString(myHolder1.iCount);


                    Log_Tost.Log_v("Button Plus",sCount);
                    myHolder1.tvItemCount.setText(sCount);
                    if(alItemName_temp.contains(myHolder1.tvItemName.getText().toString()))
                    {

                        int index=alItemName_temp.indexOf(myHolder1.tvItemName.getText().toString());
                         int price=alItemPrice.get(position);
                        Log_Tost.Log_v("Item Price",alItemPrice.get(position)+"");
                        int count=Integer.parseInt(myHolder1.tvItemCount.getText().toString());
                        int total=price*count;
                        alItemQTY_temp.set(index,Integer.parseInt(myHolder1.tvItemCount.getText().toString()));
                        alItemPrice_temp.set(index,total);
                        myHolder1.tvItemPrice.setText(Integer.toString(total));
                    }
                    else
                    {
                         alItemName_temp.add(myHolder1.tvItemName.getText().toString());
                        alItemQTY_temp.add(Integer.parseInt(myHolder1.tvItemCount.getText().toString()));
                         alItemPrice_temp.add(Integer.parseInt(myHolder1.tvItemPrice.getText().toString()));
                        int index=alItemName_temp.indexOf(myHolder1.tvItemName.getText().toString());
                        int price=alItemPrice.get(position);
                        Log_Tost.Log_v("Item Price",alItemPrice.get(position)+"");
                        int count=Integer.parseInt(myHolder1.tvItemCount.getText().toString());
                        int total=price*count;
                        myHolder1.tvItemPrice.setText(Integer.toString(total));
                    }
                    SummaryPojoClass.ClearArraylist();
                    SummaryPojoClass.setAlItemName(alItemName_temp);
                    SummaryPojoClass.setAlItemPrice(alItemPrice_temp);
                    SummaryPojoClass.setAlItemQTY(alItemQTY_temp);
                    Log_Tost.Log_v("Arraylist Size",alItemName_temp.size()+"-"+alItemQTY_temp.size()
                            +"-"+alItemPrice_temp.size());

                }
            });
            myholder.bnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyHolder myHolder1 = (MyHolder) v.getTag();
                    myHolder1.iCount = Integer.parseInt(myHolder1.tvItemCount.getText().toString());
                    int index=alItemName_temp.indexOf(myHolder1.tvItemName.getText().toString());
                    myHolder1.iCount--;
                    if(myHolder1.iCount==0)
                    {
                        Log_Tost.Toast_Msg(context,"Item QTY Can't set Zero");
                        if(alItemName_temp.isEmpty())
                        {
                            Log_Tost.Toast_Msg(context,"Item EMPTY");
                            //StoreItems.clearArrayLlist();
                           // notifyDataSetChanged();

                        }
                        else
                        {
                            alItemName_temp.remove(index);
                            alItemQTY_temp.remove(index);
                            alItemPrice_temp.remove(index);



                        }

                    }
                    else
                    {



                        String sCount = Integer.toString(myHolder1.iCount);
                        Log_Tost.Log_v("Button Minus", sCount);
                        myHolder1.tvItemCount.setText(sCount);
                        if(alItemName_temp.size()!=0)
                        {



                            if(alItemQTY_temp.get(index)!=0)
                            {
                                int price=alItemPrice.get(position);
                                int count=Integer.parseInt(myHolder1.tvItemCount.getText().toString());
                                int total=price*count;
                                alItemQTY_temp.set(index,Integer.parseInt(myHolder1.tvItemCount.getText().toString()));
                                alItemPrice_temp.set(index,total);
                                Log_Tost.Toast_Msg(context,"QTY NON ZERO");
                                myHolder1.tvItemPrice.setText(Integer.toString(total));
                            }
                            else
                            {
                                 /*StoreItems.alItemName_temp.remove(index);
                                 StoreItems.alItemQTY_temp.remove(index);
                               StoreItems. alItemPrice_temp.remove(index);

                                alItemName_temp.remove(index);
                                alItemCount.remove(index);
                                alItemPrice.remove(index);*/


                            }

                        }
                        SummaryPojoClass.ClearArraylist();
                        SummaryPojoClass.setAlItemName(alItemName_temp);
                        SummaryPojoClass.setAlItemPrice(alItemPrice_temp);
                        SummaryPojoClass.setAlItemQTY(alItemQTY_temp);
                        Log_Tost.Log_v("Arraylist Size",alItemName_temp.size()+"-"+alItemQTY_temp.size()
                                +"-"+alItemPrice_temp.size());
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

         myholder.tvItemCount.setText(Integer.toString( alItemCount.get(position)));



        return convertView;
    }

    public class MyHolder
    {

        TextView tvItemName,tvItemCount,tvItemPrice=null;
        Button bnPlus,bnMinus=null;
        int iCount=0;
    }
}
