package com.sincosmos;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
    public int evaluate(String expression){
    	int sum = 0;
    	for(String summand : expression.split("\\+")){
    		sum += Integer.valueOf(summand);
    	}
    	return sum;
    }
}
