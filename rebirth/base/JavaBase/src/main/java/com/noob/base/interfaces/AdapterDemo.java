package com.noob.base.interfaces;

import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

class RandomStrings implements Readable{

    private static Random rand = new Random(47);
    private static final char[] CAPITALS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] LOWERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] VOWELS = "aeiou".toCharArray();
    private int count;

    public RandomStrings(int count) {
        this.count = count;
    }

    @Override
    public int read(CharBuffer cb) {
        if (count-- == 0) {
            return -1; // indicates end of input
        }
        cb.append(CAPITALS[rand.nextInt(CAPITALS.length)]);
        for (int i = 0; i < 4; i++) {
            cb.append(VOWELS[rand.nextInt(VOWELS.length)]);
            cb.append(LOWERS[rand.nextInt(LOWERS.length)]);
        }
        cb.append(" ");
        return 10; // Number of characters appended
    }
}

/**
 * 接口适配demo
 */
public class AdapterDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new RandomStrings(10));
        while (sc.hasNext()) {
            System.out.println(sc.next());
        }
    }
}
