package com.noob.base.utils;

import java.io.IOException;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        try {
            List<Class<?>> classes = ClassUtil.getClasses("com.noob.base.demo.model.entity");
            classes.forEach(cls->{
                try {
                    TestUtil.TestPojo(cls);
                } catch (IllegalAccessException e) {
                } catch (InstantiationException e) {
                }
            });

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
