/*
 * @author Robert Streetman
 * @date 2014-12-21
 * @desc This class is to test and demonstrate data clustering classes. It uses an external library
 *      I wrote to read/write scientific data from files to build a data set.
 */
package jkmeans;

import java.io.File;
import java.util.Random;

/**
 * This class demonstrates the use of the Kmeans class, as well as its helper classes.
 * 
 * @author Robert Streetman
 */

public class Tester {
    
    private static int[] seeds;
    private static double[][] data;

    public static void main(String[] args) {
        
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        int i = Integer.parseInt(args[2]);
        int n = Integer.parseInt(args[3]);
        int d = Integer.parseInt(args[4]);
        //data = new double[d][n];
        data = DataFileReader.ReadeCSVFile(new File(filename));
        
        randomSeeds(k, n);  //This will eventually be replaced by k-means++
        
        double[][]centroids = KMeans.kmeans(data, k, i, seeds);
        
        System.out.println("\nClustering complete. Final centroids:");
        
        for (int j = 0; j < centroids.length; j++) {
            String line = "Centroid " + j + ": ";
            
            for (int m = 0; m < centroids[0].length; m++) {
                line += "\t" + centroids[j][m];
            }
            
            System.out.println(line);
        }
    }
    
    /**
     * This method randomly selects the indices of K points to use as the initial centroids for seeding.
     * @param k Number of clusters that exist in the data.
     * @param n Total number of candidate points in the data.
     */
    private static void randomSeeds(int k, int n) {
        seeds = new int[k];
        int count = 0;
        
        while (count < k) {
            Random rand = new Random();
            int pos = rand.nextInt(n);
            boolean knew = true;
            
            for (int i = 0; i < count; i++) {
                if (pos == seeds[i]) {
                    knew = false;
                }
            }
            
            if(knew) {
                seeds[count] = pos;
                count++;
            }
        }
    }
}
