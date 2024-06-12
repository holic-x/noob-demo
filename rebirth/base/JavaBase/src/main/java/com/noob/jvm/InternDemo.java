package com.noob.jvm;

import java.util.UUID;

public class InternDemo {
    static String getLongStr() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append(UUID.randomUUID().toString());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        while (true) {
            getLongStr().intern();
        }
    }
}