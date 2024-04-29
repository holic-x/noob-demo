package com.noob.base.generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 16:28
 */
public class Employee {

    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<Employee>();
        Employee e1 = new Employee("小C",1000,new MyDate(2004,03,23));
        Employee e2 = new Employee("小C",10000,new MyDate(1997,05,24));
        Employee e3 = new Employee("小A",8000,new MyDate(1968,12,16));
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);

        Comparator comparator = new Comparator<Employee>() {

            @Override
            public int compare(Employee o1, Employee o2) {
                // 姓名相同比较年份
                if(o1.getName().equals(o2.getName())){
                   return o1.getBirthDay().toString().compareTo(o2.getBirthDay().toString());
                }else {
                    return o1.getName().compareTo(o2.getName());
                }
            }
        };
        employeeList.sort(comparator);
        for(Employee employee : employeeList){
            System.out.println(employee);
        }
    }


    private String name;
    private double sal;
    private MyDate birthDay;

    public Employee(String name, double sal, MyDate birthDay) {
        this.name = name;
        this.sal = sal;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public MyDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(MyDate birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", birthDay=" + birthDay +
                '}';
    }
}


class MyDate{
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }
}
