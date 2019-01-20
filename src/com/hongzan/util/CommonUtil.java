package com.hongzan.util;

import com.hongzan.base.Page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 基础工具类
 */
public final class CommonUtil {

    private CommonUtil() {
    }

    /**
     * 判斷是否引用對象為空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * 判斷是否字符串對象為空
     */
    public static boolean isNull(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 判斷是否字符串對象為空
     */
    public static boolean isNull(ArrayList<?> list) {
        return list.isEmpty();
    }

    /**
     * 获取编号字符串
     */
    public static String getNumber() {
        int number;//存放随机数
        char code;//用于存放生成的字符
        String str = "";
        //实例化对象
        Random rand = new Random();
        //循环几位数
        for (int i = 0; i < 10; i++) {
            //产生随机数
            number = rand.nextInt(100 + 1) - 1;
            //随机数能被2整除时赋值余数
            if (number % 2 == 0) {
                code = (char) ('0' + (char) (number % 10));
            } else {
                //不能被2整除赋值A-Z
                code = (char) ('A' + (char) (number % 26));
            }
            //将所有字符赋值给新字符串
            str += code;
        }
        return str;
    }

    /** 获取uuid */
    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 强转至Page类型
     * @param object 对象
     * @return Page对象
     */
    public static Page<?> objectToPage(Object object) {
        if (object instanceof Page<?>) {
            return (Page<?>)object;
        }else {
            return null;
        }
    }
    /** 获取时间 */
    public static String  getTime() {
        Date date=new Date();
        long datestr=date.getTime();
        return datestr+"";
    }
    /** 获取时间 */
    public static String  getTime(String pre) {
        Date date=new Date();
        String datestr=date.getTime()+"";
        return datestr.substring(0,6).concat(pre);
    }
    /** 转成数组 */
    public static String []  toAarray(String str) {
        return str.split("-");
    }


}
