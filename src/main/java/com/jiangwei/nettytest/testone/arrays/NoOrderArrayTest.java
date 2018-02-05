package com.jiangwei.nettytest.testone.arrays;

/**
 * Created by weijiang
 * Date: 2018/2/4
 * DESC:
 */
public class NoOrderArrayTest {

    public static void main(String[] args) {
        NoOrderArray noOrderArray = new NoOrderArray(10);
        noOrderArray.addEle(1);
        noOrderArray.addEle(2);
        noOrderArray.addEle(6);
        noOrderArray.addEle(10);
        noOrderArray.addEle(3);
        noOrderArray.addEle(8);
        noOrderArray.addEle(4);
        noOrderArray.addEle(5);
        noOrderArray.addEle(23);
        noOrderArray.addEle(11);
        noOrderArray.addEle(1000001);
        noOrderArray.addEle(1000001);
        noOrderArray.addEle(1000001);
        noOrderArray.addEle(1000001);

        System.out.print("数组初始化后结果为： ");
        for(int x=0; x<noOrderArray.getLength(); x++) {
            System.out.print(noOrderArray.getEle(x)+",");
        }

        System.out.println();
        noOrderArray.delEle(1);
        System.out.print("数组删除元素结果为： ");
        for(int x=0; x<noOrderArray.getLength(); x++) {
            System.out.print(noOrderArray.getEle(x)+",");
        }
        System.out.println("\n删除后数组长度为："+noOrderArray.getLength());
    }
}
