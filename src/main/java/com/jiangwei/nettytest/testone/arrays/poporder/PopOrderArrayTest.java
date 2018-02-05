package com.jiangwei.nettytest.testone.arrays.poporder;

/**
 * Created by weijiang
 * Date: 2018/2/4
 * DESC:
 */
public class PopOrderArrayTest {
    public static void main(String[] args) {
        MyArray myArray = new MyArray(10);
        myArray.insertEle(1);
        myArray.insertEle(5);
        myArray.insertEle(8);
        myArray.insertEle(9);
        myArray.insertEle(3);
        myArray.insertEle(7);
        myArray.insertEle(2);
        myArray.insertEle(4);
        myArray.insertEle(6);
        myArray.insertEle(10);

        myArray.dispalyEle();


        myArray.popOrder();

        myArray.dispalyEle();



    }
}
