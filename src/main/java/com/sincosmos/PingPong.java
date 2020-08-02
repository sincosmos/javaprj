package com.sincosmos;

public class PingPong
{
    public static synchronized void main(String[] a) throws Exception
    {
        Thread t = new Thread(){
            public void run(){ pong(); }
        };
        t.start();
        System.out.println( "Ping" );
        System.out.println(Byte.MAX_VALUE==0x7f);
    }

    static synchronized void pong(){
        System.out.print( "Pong" );
    }
}

