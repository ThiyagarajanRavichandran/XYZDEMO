package com.xyz.com.xyzresturent.com.xyz.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xyz.com.xyzresturent.R;

import java.util.ArrayList;

/**
 * Created by THIYAGARAJAN-PC on 26/03/2016.
 */
public class RvItemViewAdapter extends RecyclerView.Adapter<RvItemViewAdapter.MyHolder> {

    private ArrayList<String> alItemId, alItemName = null;
    private ArrayList<Integer> alItemPrice, alItemQTY = null;
    private Context context = null;
    private AlertDialog.Builder builder = null;
    private Dialog dialog = null;
    private View viewDialog;
    private EditText editText = null;

    private String sItemId;
    private MyHolder myHolder;
    private LayoutInflater layoutInflater;

    public RvItemViewAdapter(Context context, ArrayList<String> alItemId, ArrayList<String> alItemName, ArrayList<Integer> alItemPrice) {
        this.alItemId = alItemId;
        this.alItemName = alItemName;
        this.alItemPrice = alItemPrice;
        this.context = context;
    }

    @Override
    public RvItemViewAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.additemview, parent, false);
        myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final RvItemViewAdapter.MyHolder holder, int position) {
        //posistion_temp=position;

        holder.tvItemId.setText(alItemId.get(position));
        holder.tvItemName.setText(alItemName.get(position));
        holder.tvItemPrice.setText(Integer.toString(alItemPrice.get(position)));

        holder.bnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log_Tost.Toast_Msg(context, sItemId);
                sItemId = holder.tvItemId.getText().toString();
                removeItem(sItemId);
            }
        });
         layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        holder.tvItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             View   viewDialog = layoutInflater.inflate(R.layout.itemedit, null);
                editText = (EditText) viewDialog.findViewById(R.id.editItem);
                editText.setText(holder.tvItemName.getText().toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Update Items");
                builder.setMessage("Updateing items names...");
                builder.setCancelable(true);
                builder.setView(viewDialog);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sUpdateValues = editText.getText().toString();
                        if (!sUpdateValues.isEmpty()) {
                            if (sUpdateValues.equals(holder.tvItemName.getText().toString())) {
                                Log_Tost.Toast_Msg(context, "Item name is same");
                            } else {
                                long id = Common_Method.dataBaseAdapter.UpdateItems(sUpdateValues, holder.tvItemId.getText().toString(),
                                        "ItemName");
                                if (id > 0) {
                                    Log_Tost.Toast_Msg(context, "Item updated successfully");
                                    dialog.dismiss();
                                } else {
                                    Log_Tost.Toast_Msg(context, "Item update fails");
                                    dialog.dismiss();
                                }
                            }
                        } else {


                        }

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }

        });
        holder.tvItemPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View  viewDialog = layoutInflater.inflate(R.layout.itemedit, null);

                editText = (EditText) viewDialog.findViewById(R.id.editItem);
                editText.setText(holder.tvItemPrice.getText().toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Update Items");
                builder.setMessage("Updateing items price...");
                builder.setCancelable(true);
                builder.setView(viewDialog);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sUpdateValues = editText.getText().toString();
                        if (!sUpdateValues.isEmpty()) {
                            if (sUpdateValues.equals(holder.tvItemPrice.getText().toString())) {
                                Log_Tost.Toast_Msg(context, "Item name is same");
                            } else {
                                long id = Common_Method.dataBaseAdapter.UpdateItems(sUpdateValues, holder.tvItemId.getText().toString(),
                                        "Price");
                                if (id > 0) {
                                    Log_Tost.Toast_Msg(context, "Price updated successfully");
                                } else {
                                    Log_Tost.Toast_Msg(context, "Price update fails");
                                }
                            }
                        } else {


                        }

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    private void removeItem(String sItemId) {
        int position = alItemId.indexOf(sItemId);
        alItemId.remove(position);
        alItemName.remove(position);
        alItemPrice.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return alItemId.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvItemId, tvItemName, tvItemPrice = null;
        private Button bnDelete = null;

        public MyHolder(View itemView) {
            super(itemView);
            tvItemId = (TextView) itemView.findViewById(R.id.addtvItemId);
            tvItemName = (TextView) itemView.findViewById(R.id.addtvItemName);
            tvItemPrice = (TextView) itemView.findViewById(R.id.ItemPrice);
            bnDelete = (Button) itemView.findViewById(R.id.bnDelete);

        }
    }
}
