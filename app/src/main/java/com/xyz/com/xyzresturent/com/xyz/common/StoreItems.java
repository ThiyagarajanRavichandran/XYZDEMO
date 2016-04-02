package com.xyz.com.xyzresturent.com.xyz.common;

import java.util.ArrayList;

/**
 * Created by THIYAGARAJAN on 01-04-2016.
 */
public class StoreItems {

    public static ArrayList<String> alItemName_temp=new ArrayList<>();
    public static ArrayList<Integer>alItemPrice_temp=new ArrayList<>();
    public static ArrayList<Integer>alItemQTY_temp=new ArrayList<>();

public static void clearArrayLlist()
{
    alItemName_temp.clear();
    alItemPrice_temp.clear();
    alItemQTY_temp.clear();
}
}
