package com.noob.base.reflect;

import java.util.ArrayList;
import java.util.List;

/**
 * instanceof demo
 */
public class InstanceofDemo {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        if(arrayList instanceof List){
            System.out.println("ArrayList is List");
        }
        if(List.class.isInstance(arrayList)){
            System.out.println("ArrayList is List");
        }
    }
}
