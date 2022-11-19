package com.example.demo.api;

import java.io.UnsupportedEncodingException;

public class Isok {
    public static boolean isStr2Num(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isbetween(String str , int minLength,Integer maxLength) {
        try {
            int length = str.getBytes("GBK").length;
            if(length > maxLength || length<minLength) {
                return false;
            }else {
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }
    public static boolean isover(String str , int minLength) {
        try {
            int length = str.getBytes("GBK").length;
            if(length<minLength) {
                return false;
            }else {
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }


}
