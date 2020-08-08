package com.sincosmos;

public class RecursiveDependency {
    public static void main(String[] args){
        A a = new A();
        B b = new B();
        b.setA(a);
        a.setB(b);
        System.out.println(a.getA());
    }
}

class A{
    private B b;;
    private int a = 10;
    public int getA(){return a;}
    public void setB(B b) {
        this.b = b;
    }
}
class B{
    private A a;
    public void setA(A a) {
        this.a = a;
    }
}
