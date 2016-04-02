package com.xyz.com.xyzresturent.com.xyz.common;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by THIYAGARAJAN-PC on 24/10/2015.
 */
public   class Log_Tost  {



    public static void Log_e(String sTag,String sMessage)
    {
        Log.e(sTag, sMessage);
    }
    public static void Log_i(String sTag,String sMessage)
    {
        Log.i(sTag, sMessage);
    }
    public static void Log_v(String sTag,String sMessage)
    {
        Log.v(sTag, sMessage);
    }
    public static void Toast_Msg(Context context,String sMessage)
    {

        Toast.makeText(context,sMessage,Toast.LENGTH_SHORT).show();
    }
    public static void RunInSeparateThread() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
