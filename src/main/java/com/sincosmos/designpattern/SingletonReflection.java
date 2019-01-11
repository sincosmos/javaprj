package com.sincosmos.designpattern;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SingletonReflection {
    public static void main(String args[]){
        try {
            Method method = BetterSingleton.class.getMethod("getInstance");
            BetterSingleton singleton = (BetterSingleton)method.invoke(null);
            singleton.test();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

class Singleton{
    private Singleton(){}
    private static final Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }

    public void test(){
        System.out.println("Singleton is being used");
    }
}

class BetterSingleton{
    private BetterSingleton(){}
    private static class Holder{
        private static final BetterSingleton singleton = new BetterSingleton();
    }
    public static BetterSingleton getInstance(){
        return Holder.singleton;
    }

    public void test(){
        System.out.println("BetterSingleton is being used");
    }
}
