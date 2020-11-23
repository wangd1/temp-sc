package com.wang.sc.demos.test;

/**
 * @PackageName: com.wang.sc.demos.test
 * @ClassName: DemoTest
 * @Description:
 * @Author: wangdi
 * @Date: 2020/11/15 11:15
 */
public class DemoTest {

    public static void main(String[] args) {
        String str1 = new String("aaa");
        String str2 = str1.intern();
        System.out.println(str1==str2);


        String str3 = new StringBuilder().append("计算机").append("科学").toString();
        String str4 = str3.intern();
        System.out.println(str3==str4);
    }
}
