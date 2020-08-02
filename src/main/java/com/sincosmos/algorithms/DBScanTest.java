package com.sincosmos.algorithms;;

import de.lmu.ifi.dbs.elki.algorithm.clustering.DBSCAN;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.clustering.DoublePoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DBScanTest {
    public static void main(String[] args){
        DBSCANClusterer cluster = new DBSCANClusterer(2, 3);

        List<DoublePoint> coordinates = new ArrayList<>();
        long st = System.currentTimeMillis();
        Random rand = new Random(17);
        for(int i=0; i<1000; ++i){
            double[] points = new double[2];
            points[0] = Math.round(rand.nextDouble() * 100000) / 100.0;
            points[1] = Math.round(rand.nextDouble() * 100000) / 100.0;
            coordinates.add(new DoublePoint(points));
        }


        //coordinates.stream().forEach(System.out::println);
        System.out.println("\n\n");
        List<Cluster<DoublePoint>> clusters = cluster.cluster(coordinates);
        System.out.println(clusters.size());
        clusters.stream().forEach(x->{
            List<DoublePoint> tmp = x.getPoints();
            //System.out.println(tmp.size());
            System.out.println("==============");
            tmp.stream().forEach(y->System.out.print(y + ", "));
            System.out.println("\n==============");
        });
        long ed = System.currentTimeMillis();
        System.out.println(ed-st);

        //DBSCAN
    }
}
