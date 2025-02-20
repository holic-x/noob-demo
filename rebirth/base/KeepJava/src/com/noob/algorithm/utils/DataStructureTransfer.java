package com.noob.algorithm.utils;

import java.util.*;

public class DataStructureTransfer {


    public static int[] setToArray(){
        Set<Integer> set= new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        return set.stream().mapToInt(Integer::valueOf).toArray();
    }


    public static int[] listToArray(){
        List<Integer> list= new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    /*
    public static Set arrayToSet(){
        int[] array = new int[]{1,2,3};
        return Arrays.asList(array);
    }
     */

    public static List arrayToList(){
        int[] array = new int[]{1,2,3};
        return Arrays.asList(array);
    }

    public static void main(String[] args) {
        // 1.set to array
        int[] res1 = setToArray();

        // 2.list to array
        int[] res2 = listToArray();

        // array to list
        List<Integer> res4 = arrayToList();

    }


}
