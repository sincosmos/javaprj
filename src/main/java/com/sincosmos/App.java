package com.sincosmos;

import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

    }
    
    public int evaluate(String expression){
    	int sum = 0;
    	for(String summand : expression.split("\\+")){
    		sum += Integer.valueOf(summand);
    	}
    	return sum;
    }


    static double calculateDistance(double latPoint1, double lngPoint1,
                             double latPoint2, double lngPoint2) {
        if(latPoint1 == latPoint2 && lngPoint1 == lngPoint2) {
            return 0d;
        }

        final double EARTH_RADIUS = 6371.0; //km value;

        //converting to radians
        latPoint1 = Math.toRadians(latPoint1);
        lngPoint1 = Math.toRadians(lngPoint1);
        latPoint2 = Math.toRadians(latPoint2);
        lngPoint2 = Math.toRadians(lngPoint2);

        double distance = Math.pow(Math.sin((latPoint2 - latPoint1) / 2.0), 2)
                + Math.cos(latPoint1) * Math.cos(latPoint2)
                * Math.pow(Math.sin((lngPoint2 - lngPoint1) / 2.0), 2);
        distance = 2.0 * EARTH_RADIUS * Math.asin(Math.sqrt(distance));

        return distance; //km value
    }
}

