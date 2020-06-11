package com.pupu;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Test02 {

    public static void main(String[] args) {

//        List<Integer> list = new ArrayList<Integer>();
//
//        for (int i = 0; i < 30; i++) {
//            final int ii = i;
//            new Thread(() -> {
//                list.add(ii);
//                System.out.println(list);
//            }).start();
//        }

        Class myClass;
        Constructor constructor;
        Constructor constructor2;
        Field field;
        Method method;
        Mystery mystery;

        try {
            myClass = Class.forName("com.pupu.Mystery");

            /*无参构造*/
            constructor = myClass.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            mystery = (Mystery) constructor.newInstance();
            System.out.println(mystery.getHaha());

            /*有参构造*/
            constructor2 = myClass.getDeclaredConstructor(String.class,int.class);
            constructor2.setAccessible(true);
            mystery = (Mystery) constructor2.newInstance("neal",30);

            /*修改字段*/
            field = myClass.getDeclaredField("haha");
            field.setAccessible(true);
            field.set(mystery,"hehe");
            System.out.println(mystery.getHaha());

            /*调用方法*/
            method = myClass.getDeclaredMethod("haveFun",String.class);
            method.setAccessible(true);
            method.invoke(mystery,"444");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }



}

class Mystery{
    private String haha = "haha";

    private Mystery(){
        System.out.println("where are you from?");
    }

    private Mystery(String name,int i){
        System.out.println("my name is "+name+",age is"+i);
    }

    private void haveFun(String arg){
        System.out.println("lalallalalal"+arg);
    }

    public String getHaha(){
        return haha;
    }

}
