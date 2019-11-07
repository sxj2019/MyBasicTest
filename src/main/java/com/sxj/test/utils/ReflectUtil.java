package com.sxj.test.utils;

import java.lang.reflect.*;

/**
 * @program: test02
 * @description:
 * @author: sxj
 * @create: 2019-10-29 20:11
 **/
public class ReflectUtil {

    public static void printConstructor(Class cl,StringBuilder sb) {
        Constructor[] constructors = cl.getDeclaredConstructors();
        for (Constructor con : constructors){
            String modifier = Modifier.toString(con.getModifiers());
            String conName = con.getName();
            sb.append(modifier).append(" ");
            sb.append(conName).append(" (");
            Parameter[] parameters = con.getParameters();
            for (Parameter parameter : parameters){
                Class<?> type = parameter.getType();
                String parameterName = parameter.getName();
                sb.append(type.getName()).append(" ");
                sb.append(parameterName).append(",");
            }
            sb.append(" )\n");
        }
    }

    public static void printField(Class cl,StringBuilder sb) {
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields){
            String modifier = Modifier.toString(field.getModifiers());
            sb.append(modifier).append(" ");
            Class<?> type = field.getType();
            String fieldName = field.getName();
            sb.append(type.getName()).append(" ");
            sb.append(fieldName).append(";\n");
        }
    }

    public static void printMethod(Class cl,StringBuilder sb) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods){
            String modifier = Modifier.toString(method.getModifiers());
            Class<?> returnType = method.getReturnType();
            String methodName = method.getName();
            sb.append(modifier).append(" ");
            sb.append(returnType.getName()).append(" ");
            sb.append(methodName).append("( ");
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters){
                Class<?> type = parameter.getType();
                String parameterName = parameter.getName();
                sb.append(type.getName()).append(" ");
                sb.append(parameterName).append(", ");
            }
            sb.append(" )\n");
        }
    }
}
