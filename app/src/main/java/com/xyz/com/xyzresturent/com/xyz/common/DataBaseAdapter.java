package com.xyz.com.xyzresturent.com.xyz.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xyz.com.xyzresturent.R;

import java.util.ArrayList;


/**
 * Created by THIYAGARAJAN-PC on 3/8/2016.
 */
public class DataBaseAdapter {
    private final DataBaseHelper dataBaseHelper;

    public DataBaseAdapter(Context context) {
        this.dataBaseHelper = new DataBaseHelper(context);
    }


    public long insert_table(ArrayList<String> al_tableNames) {
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log_Tost.Log_v("Insert Mthoed", "Called");
        long id = -1;
        for (String sValues : al_tableNames) {
            contentValues.put("vcTableNo", sValues);
            contentValues.put("vcTotalCovers", "4");
            contentValues.put("vcTableBooked", "0");
            id = sqLiteDatabase.insert(DataBaseHelper.sTN_Table, null, contentValues);
        }

        sqLiteDatabase.close();
        return id;

    }

    public long insert_Subcat(ArrayList<String> alSubCat) {
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log_Tost.Log_v("Insert Mthoed", "Called");
        long id = -1;
        for (String sValues : alSubCat) {
            contentValues.put("vcSubcatId", sValues.substring(0, sValues.indexOf(",")));
            contentValues.put("vcSubcatName", sValues.substring(sValues.indexOf(",") + 1));
            id = sqLiteDatabase.insert(DataBaseHelper.sTN_SubCat, null, contentValues);
        }

        sqLiteDatabase.close();
        return id;
    }

    public long insert_item(ArrayList<String> alItem) {
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log_Tost.Log_v("Insert Mthoed", "Called");
        long id = -1;
        for (String sValues : alItem) {
            contentValues.put("vcItemId", sValues.substring(0, sValues.indexOf("!")));
            Log_Tost.Log_v("vcItemId***", sValues.substring(0, sValues.indexOf("!")));

            contentValues.put("vcIteName", sValues.substring(sValues.indexOf("!") + 1, sValues.indexOf("@")));
            Log_Tost.Log_v("vcIteName***", sValues.substring(sValues.indexOf("!") + 1, sValues.indexOf("@")));

            contentValues.put("vcSubId", sValues.substring(sValues.indexOf("@") + 1, sValues.indexOf("#")));
            Log_Tost.Log_v("vcSubId***", sValues.substring(sValues.indexOf("@") + 1, sValues.indexOf("#")));

            contentValues.put("vcItemPrice", sValues.substring(sValues.indexOf("#") + 1));
            Log_Tost.Log_v("vcItemPrice***", sValues.substring(sValues.indexOf("#") + 1));
            id = sqLiteDatabase.insert(DataBaseHelper.sTN_Item, null, contentValues);
        }

        sqLiteDatabase.close();
        return id;
    }

    public long insert_OrderTable(String sOrderId, String sTableId) {
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log_Tost.Log_v("Insert_OrderTable", "Called");
        long id = -1;

        contentValues.put("vcOrderId", sOrderId);
        Log_Tost.Log_v("vcOrderId***", sOrderId);

        contentValues.put("vcTableId", sTableId);
        Log_Tost.Log_v("vcTableId***", sTableId);


        id = sqLiteDatabase.insert(DataBaseHelper.sTN_OrderTable, null, contentValues);


        sqLiteDatabase.close();
        return id;
    }

    public int Data_Test() {
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String[] SA_ColumnName = {"vcTableNo", "vcTotalCovers", "vcTableBooked"};
        /*Cursor is used to get the value from database and use If next is available or not*/
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.sTN_Table, SA_ColumnName, null, null, null, null, null);


        /*After getting or inserting the data need to close both DB and Cursor connection*/
        /*cursor.close();
        sqLiteDatabase.close();*/
        return cursor.getCount();

    }

    /*For getting all data from database*/
    public String getallTabledata(ArrayList<String> alTableNo, ArrayList<Integer> alTableCovers, ArrayList<Integer> alTableBooked) {
        alTableNo.clear();
        alTableCovers.clear();
        alTableBooked.clear();
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String[] SA_ColumnName = {"vcTableNo", "vcTotalCovers", "vcTableBooked"};
        /*Cursor is used to get the value from database and use If next is available or not*/
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.sTN_Table, SA_ColumnName, null, null, null, null, null);
        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
            int iTableNo = cursor.getColumnIndex("vcTableNo"),
                    iTableCovers = cursor.getColumnIndex("vcTotalCovers"), iTableBooked = cursor.getColumnIndex("vcTableBooked");
            String sTableNo = cursor.getString(iTableNo);
            int iTableTotalCovers = cursor.getInt(iTableCovers), iTableBookedStatus = cursor.getInt(iTableBooked);
            stringBuilder.append(sTableNo).append("-").append(iTableTotalCovers).append("-").append(iTableBookedStatus).append("\n");
            Log_Tost.Log_v("AllDatas***", stringBuilder.toString());
            alTableNo.add(sTableNo);
            alTableCovers.add(iTableTotalCovers);
            alTableBooked.add(iTableBookedStatus);
        }
        /*After getting or inserting the data need to close both DB and Cursor connection*/
        /*cursor.close();
        sqLiteDatabase.close();*/
        return stringBuilder.toString();

    }

    public String getAllItem(ArrayList<String> alItemName, ArrayList<String> alItemId, ArrayList<Integer> alItemPrice) {
        alItemName.clear();
        alItemPrice.clear();
        alItemId.clear();

        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String[] SA_ColumnName = {"vcItemId", "vcIteName", "vcItemPrice"};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.sTN_Item, SA_ColumnName, null, null, null, null, null);
        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
            int iItemId = cursor.getColumnIndex("vcItemId"),
                    iItemName = cursor.getColumnIndex("vcIteName"), iItemPrice = cursor.getColumnIndex("vcItemPrice");
            String sItemId = cursor.getString(iItemId), sItemName = cursor.getString(iItemName);
            int itemprice = cursor.getInt(iItemPrice);
            stringBuilder.append(sItemId).append("-").append(sItemName).append("-").append(itemprice).append("\n");
            Log_Tost.Log_v("AllDatas***", stringBuilder.toString());
            alItemId.add(sItemId);
            alItemName.add(sItemName);
            alItemPrice.add(itemprice);


        }
        return stringBuilder.toString();
    }

    public String getOrderTable(Context context) {
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String[] SA_ColumnName = {"vcTableId", "vcOrderId"};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.sTN_OrderTable, SA_ColumnName, null, null, null, null, null);
        StringBuilder stringBuilder = new StringBuilder();
        Log_Tost.Log_v("Curosr Count", cursor.getCount() + "");
        if (cursor.getCount() > 0) {

            String sOrderId = null;
            int temp_orderId = 0;
            while (cursor.moveToNext()) {
                //cursor.moveToPosition(cursor.getCount());
                if (cursor.isLast()) {
                    int iTableId = cursor.getColumnIndex("vcTableId"),
                            iOrderId = cursor.getColumnIndex("vcOrderId");
                    sOrderId = cursor.getString(iOrderId);
                    temp_orderId = Integer.parseInt(sOrderId.substring(3).trim());
                    Log_Tost.Log_v("OrderId if while", sOrderId.substring(3).trim());
                    temp_orderId++;
                }


            }
            stringBuilder.append(sOrderId.substring(0, 3).concat(Integer.toString(temp_orderId))).append("\n");
            Log_Tost.Log_v("OrderId if", stringBuilder.toString());

        } else {
            stringBuilder.append(context.getResources().getString(R.string.OrderId));
        }
        Log_Tost.Log_v("OrderId***", stringBuilder.toString());
        return stringBuilder.toString();
    }

    public long UpdateTable(String sTableNo, String sStatus) {
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String[] sRowid = {sTableNo};
        long id = -1;

        contentValues.put("vcTableBooked", sStatus);


        id = sqLiteDatabase.update(DataBaseHelper.sTN_Table, contentValues, "vcTableNo=?", sRowid);


        sqLiteDatabase.close();
        return id;
    }

    public long UpdateItems(String sUpdateValue, String sItemId, String sColumn) {
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long id = -1;
        String[] sRowid = {sItemId};
        if (sColumn.equals("Price")) {
            contentValues.put("vcItemPrice", sUpdateValue);
        } else {
            contentValues.put("vcIteName", sUpdateValue);
        }
        id = sqLiteDatabase.update(DataBaseHelper.sTN_Item, contentValues, "vcItemId=?", sRowid);


        sqLiteDatabase.close();
        return id;
    }

    static class DataBaseHelper extends SQLiteOpenHelper {
        private static final String sDBName = "xyzResturent";
        private static final String sTN_CusDetails = "customerdetails", sTN_Item = "item", sTN_OrderItems = "orderitems",
                sTN_OrderTable = "ordertable", sTN_SubCat = "subcat", sTN_Table = "tableno";
        private static final int iDATABASE_VERSION = 1;
        private static final String sCustomerDetails = "CREATE TABLE " + sTN_CusDetails + "(" + "vcCusId" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "vcCusName" + " VARCHAR(255),vcCusPhoneNo INTEGER,vcTableId INTEGER);";
        private static final String sTable = "CREATE TABLE " + sTN_Table + "(vcTableId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "vcTableNo VARCHAR(255),vcTotalCovers INTEGER,vcTableBooked INTEGER);";
        private static final String sItem = "CREATE TABLE " + sTN_Item + "(" + "vcItemId" + " VARCHAR(255),"
                + "vcIteName" + " VARCHAR(255),vcSubId VARCHAR(255),vcItemPrice INTEGER);";
        private static final String sOrderItem = "CREATE TABLE " + sTN_OrderItems + "(" + "vcOrderId" + " VARCHAR(255),"
                + "vcItemId" + " VARCHAR(255),vcItemQty INTEGER,vcTotalPrice INTEGER,vcSubCatId VARCHAR(255));";
        private static final String sOrderTable = "CREATE TABLE " + sTN_OrderTable + "(" + "vcId" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "vcTableId" + " INTEGER,vcOrderId VARCHAR(255));";
        private static final String sSubCat = "CREATE TABLE " + sTN_SubCat + "(" + "vcId" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "vcSubcatId" + " VARCHAR(255),vcSubcatName VARCHAR(255));";
        private ArrayList<String> alQuries_List, alDropQuies_List;
        // private static final String sDROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public DataBaseHelper(Context context) {
            super(context, sDBName, null, iDATABASE_VERSION);
            this.context = context;
            Log_Tost.Toast_Msg(context, "Constructor called");
            alQuries_List = new ArrayList<>();
            alDropQuies_List = new ArrayList<>();
            alQuries_List.clear();
            alDropQuies_List.clear();
            if (alQuries_List != null) {
                alQuries_List.add(sOrderTable);
                alQuries_List.add(sCustomerDetails);
                alQuries_List.add(sTable);
                alQuries_List.add(sItem);
                alQuries_List.add(sOrderItem);
                alQuries_List.add(sSubCat);
                alDropQuies_List.add(sTN_Table);
                alDropQuies_List.add(sTN_CusDetails);
                alDropQuies_List.add(sTN_Item);
                alDropQuies_List.add(sTN_OrderItems);
                alDropQuies_List.add(sTN_OrderTable);
                alDropQuies_List.add(sTN_SubCat);
            }
            //Log_Tost.Log_v("Arraylist is NON empty", alQuries_List.size()+"");


        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if (alQuries_List.size() != 0) {
                Log_Tost.Log_v("Arraylist is NON empty", alQuries_List.size() + "");

                for (String sQuery : alQuries_List) {
                    try {
                        db.execSQL(sQuery);
                        Log_Tost.Log_v("onCreate called", "success");
                    } catch (SQLException e) {
                        Log_Tost.Log_e("DATABASE ERROR", e.getMessage());
                    }
                }
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            for (String sDrop_Query : alDropQuies_List) {
                db.execSQL(dropTable(sDrop_Query));
                onCreate(db);
            }
        }

        private String dropTable(String sDrop_query) {
            return "DROP TABLE IF EXISTS " + sDrop_query;
        }

    }

}
