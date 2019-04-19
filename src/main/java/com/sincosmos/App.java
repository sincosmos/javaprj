package com.sincosmos;

import ch.hsr.geohash.GeoHash;
import ch.hsr.geohash.WGS84Point;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        /*App app = new App();
        double d = app.distance(GeoHash.fromGeohashString("wx4g0q2v").getPoint(),
                GeoHash.fromGeohashString("wx4fk77q").getPoint());//wx4g837n
        System.out.println(d);

        d = app.distance(39.937623, 39.938101, 116.379339, 116.377967, 0, 0);
        System.out.println(7/2);*/
        LocalDate end = LocalDate.now().minusDays(1);
        System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("a".substring(1) + "--");
    }


    /**
     * 计算两 GPS 点之间的距离
     * @param p1 gps 点
     * @param p2 gps 点
     * @return 两点之间的距离，单位为米
     */
    private double distance(WGS84Point p1, WGS84Point p2){
        return distance(p1.getLatitude(), p2.getLatitude(),
                p1.getLongitude(), p2.getLongitude(), 0.0, 0.0);
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point
     * lat2, lon2 End point
     * el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    private double distance(double lat1, double lat2, double lon1,
                            double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}

