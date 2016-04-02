package com.xyz.com.xyzresturent.com.xyz.common;

import java.util.ArrayList;

/**
 * Created by THIYAGARAJAN-PC on 25/03/2016.
 */
public class SummaryPojoClass {

    public static ArrayList<String>alItemName=new ArrayList<>();
    public static ArrayList<Integer>alItemPrice=new ArrayList<>();
    public static ArrayList<Integer>alItemQTY=new ArrayList<>();

    public static ArrayList<Integer> getAlItemPrice() {
        return alItemPrice;
    }

    public static void setAlItemPrice(ArrayList<Integer> alItemPrice) {
        SummaryPojoClass.alItemPrice = alItemPrice;
    }

    public static ArrayList<String> getAlItemName() {
        return alItemName;
    }

    public static void setAlItemName(ArrayList<String> alItemName) {
        SummaryPojoClass.alItemName = alItemName;
    }

    public static ArrayList<Integer> getAlItemQTY() {
        return alItemQTY;
    }

    public static void setAlItemQTY(ArrayList<Integer> alItemQTY) {
        SummaryPojoClass.alItemQTY = alItemQTY;
    }

public static void ClearArraylist()
{
    alItemName.clear();
    alItemPrice.clear();
    alItemQTY.clear();
}

}
