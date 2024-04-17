package com.noob.base.methodReference;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName CarTest
 * @Description 方法引用测试Car
 * @Author Huh-x
 */
public class CarTest {
    public static void main(String[] args) {
        // 方式1：构造器引用：Class::new（或者一般的Class< T >::new）
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList( car );

        // 方式2：静态方法引用：Class::static_method
        cars.forEach( Car::collide );

        // 方式3：特定类的任意对象的方法引用：Class::method
        cars.forEach( Car::repair );

        // 方式4：特定对象的方法引用：instance::method
        final Car police = Car.create( Car::new );
        cars.forEach( police::follow );
    }
}
