package com.sxj.test.model;

import java.util.Comparator;

/**
 * @program: test02
 * @description:
 * @author: sxj
 * @create: 2019-10-31 16:31
 **/
public class Employee implements Comparable<Employee>,Cloneable{
    private String name;
    private long salary;
    static String managerName;

    public Employee() {
    }

    public Employee(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public static String getManagerName() {
        return managerName;
    }

    public static void setManagerName(String managerName) {
        Employee.managerName = managerName;
    }


    @Override
    public int compareTo(Employee o) {
        return Long.compare(this.salary,o.salary);
    }

    @Override
    public Employee clone() throws CloneNotSupportedException {
        return (Employee) super.clone();
    }
}
