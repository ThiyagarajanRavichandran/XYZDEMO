package com.xyz.com.xyzresturent.com.xyz.common;

import android.content.Context;

/**
 * Created by THIYAGARAJAN-PC on 3/11/2016.
 */
public class Common_Method {

    public  static DataBaseAdapter dataBaseAdapter=null;
    public String[] getResourceStringArray(Context context, int iResId)
    {
        return context.getResources().getStringArray(iResId);
    }
}
