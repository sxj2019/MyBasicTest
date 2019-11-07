package com.sxj.test.model;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: sxj
 * @create: 2019-10-31 16:40
 **/
public class ObjectAnalyzer {

    public String toString(Object obj) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Class claz = obj.getClass();
        String simpleName = claz.getSimpleName();
        if (claz.isArray()){
            Class componentType = claz.getComponentType();
            sb.append(simpleName).append(": ");
            sb.append(componentType.getName());
        }
        sb.append(simpleName).append("[");
        Field[] fields = claz.getDeclaredFields();
        AccessibleObject.setAccessible(fields,true);
        for (Field field : fields){
            String fieldName = field.getName();
            Object o = field.get(obj);
            sb.append(fieldName).append("=")
                    .append(o).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
//        Employee employee = new Employee("zhangsan",3600);
//        try {
//            String s = new ObjectAnalyzer().toString(employee);
//            System.out.println(s);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

//        List<Integer> squares = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            squares.add(i * i);
//        }
//
//        try {
//            String s = new ObjectAnalyzer().toString(squares);
//            System.out.println(s);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }


    }
}
