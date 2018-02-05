package com.jiangwei.nettytest.testone.arrays;

/**
 * Created by weijiang
 * Date: 2018/2/4
 * DESC: 无序数组
 */
public class NoOrderArray {

    private Object[] myArray;
    private int index;
    private int count;

    public NoOrderArray(int size) {
        this.myArray = new Object[size];
        index = 0;
        System.out.println("初始化数组长度为："+myArray.length);
    }

    public boolean addEle(Object t) {
        if(count < myArray.length) {
            myArray[count] = t;
            ++count;
            return true;
        }else {
            return false;
        }
    }

    public int getLength() {
        return count;
    }

    public Object getEle(int index) {
        return myArray[index];
    }

    public Object delEle(Object t) {
        Object rt = null;
        int currentIndex = 0;
       for(int temIndex=0; temIndex < count; temIndex++) {
           if(myArray[temIndex].equals(t)) {
               rt = myArray[temIndex];
               currentIndex = temIndex;
               --count;
               for(; currentIndex < count; currentIndex++) {
                   myArray[currentIndex] = myArray[currentIndex+1];
               }
               break;
           }
       }

       return rt;
    }
}
