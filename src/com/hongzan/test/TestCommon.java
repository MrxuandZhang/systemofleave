package com.hongzan.test;

import com.hongzan.util.CommonUtil;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCommon {
    Date date=new Date();
    @Test
    public void firstTest(){
       System.out.println( CommonUtil.getTime("w"));
//         System.out.println(CommonUtil.getId());
    }
}
