package com.jiangwei.nettytest.testone.arrays.poporder;

/**
 * Created by weijiang
 * Date: 2018/2/4
 * DESC:
 */
public class MyArray {


    private Integer[] objArray;
    private int count;

    public MyArray() {
    }

    public MyArray(int size) {
        this.objArray = new Integer[size];
        this.count = 0;
    }

    public void insertEle(Integer t) {
        objArray[count] = t;
        ++count;
    }


    public void dispalyEle() {
        System.out.print("数组内容为：");
        for(int x=0; x<count-1; x++) {
            System.out.print(objArray[x]+",");
        }
        System.out.print(objArray[count-1]+"\n");
    }


    public void popOrder() {
        for(int x=count-1; x>1; x--) {
            for(int y=0; y<x; y++) {
                if(this.objArray[y] > this.objArray[y+1]) {
                    swap(y, y+1);
                }
            }
        }
    }

    public void swap(int one, int two) {
        Integer temp = this.objArray[one];
        this.objArray[one] = this.objArray[two];
        this.objArray[two] = temp;
    }


    public Integer[] getObjArray() {
        return objArray;
    }

    public void setObjArray(Integer[] objArray) {
        this.objArray = objArray;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
