/**
 * @author Robert Streetman
 * @date 2014-12-21
 * @desc This class is to test and demonstrate data clustering classes. It uses an external library
 *      I wrote to read/write scientific data from files to build a data set.
 */
package jkmeans;

import dataio.TxtReader;
import java.util.Random;

public class KmeansTester {
    
    private static int[] seeds;
    private static double[][] data;

    public static void main(String[] args) {
        //Params - "filename" K I N D
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        int i = Integer.parseInt(args[2]);
        int n = Integer.parseInt(args[3]);
        int d = Integer.parseInt(args[4]);
        data = TxtReader.ReadDouble(filename, ",", d, n);
        
        randomSeeds(k, n);  //This will eventually be replaced by k-means++
        
        double[][]centroids = Kmeans.kmeans(data, k, i, seeds);
        System.out.println("Clustering complete. Final centroids:");
        for(int j = 0; j < centroids.length; j++) {
            String line = "Centroid " + j + ": ";
            for(int m = 0; m < centroids[0].length; m++) {
                line += "\t" + centroids[j][m];
            }
            System.out.println(line);
        }
    }
    
    private static void randomSeeds(int k, int n) {
        seeds = new int[k];
        int count = 0;
        while(count < k) {
            Random rand = new Random();
            int pos = rand.nextInt(n);
            boolean knew = true;
            for(int i = 0; i < count; i++) {
                if(pos == seeds[i]) {
                    knew = false;
                }
            }
            if(knew) {
                seeds[count] = pos;
                count++;
            }
        }
        //System.out.println("Here are indices of seeds:");
        //for(int i = 0; i < k; i++) {
        //    System.out.println("Seed " + i + ": Pos = " + seeds[i]);
        //}
    }
     /*
     * This was just used to make sure there was actually data in the data points
    */
    private static void printData() {
        for(int i = 0; i < data.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Point ");
            sb.append(i);
            sb.append(":\t");
            for(int j = 0; j < data[0].length; j++) {
               sb.append(data[i][j]);
               sb.append("\t");
            }
            System.out.println(sb.toString());
        }
    }    
}
