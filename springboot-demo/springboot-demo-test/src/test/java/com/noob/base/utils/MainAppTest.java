package com.noob.base.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainAppTest {

    @Test
    void main() {
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