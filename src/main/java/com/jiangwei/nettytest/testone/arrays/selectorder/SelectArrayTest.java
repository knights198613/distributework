package com.jiangwei.nettytest.testone.arrays.selectorder;

/**
 * Created by weijiang
 * Date: 2018/2/5
 * DESC:
 */
public class SelectArrayTest {

    public static void main(String[] args) {
        SelectArray selectArray = new SelectArray(10);
        selectArray.insertEle(1);
        selectArray.insertEle(5);
        selectArray.insertEle(8);
        selectArray.insertEle(9);
        selectArray.insertEle(3);
        selectArray.insertEle(7);
        selectArray.insertEle(2);
        selectArray.insertEle(4);
        selectArray.insertEle(6);
        selectArray.insertEle(10);

        selectArray.dispalyEle();

        selectArray.selectOrder();

        selectArray.dispalyEle();
    }
}
