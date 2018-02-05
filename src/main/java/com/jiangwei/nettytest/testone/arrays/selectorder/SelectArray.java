package com.jiangwei.nettytest.testone.arrays.selectorder;

import com.jiangwei.nettytest.testone.arrays.poporder.MyArray;

/**
 * Created by weijiang
 * Date: 2018/2/5
 * DESC:
 */
public class SelectArray extends MyArray {


    public SelectArray(int size) {
        super(size);
    }

    public void selectOrder() {
        int min = 0;
        for(int out=0; out<getCount()-1; out++) {
            min = out;
            for(int in=out+1; in<getCount(); in++) {
                if(getObjArray()[in] < getObjArray()[min]) {
                    min = in;
                }
            }
            swap(out, min);
        }
    }
}
