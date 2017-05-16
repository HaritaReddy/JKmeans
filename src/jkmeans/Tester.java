package jkmeans;

import java.io.File;
import java.util.Random;

/**
 * This class demonstrates the use of the Kmeans library and helper classes. Based on code written Fall 2012.
 * 
 * @author Robert Streetman
 */

public class Tester {
    
    private static int[] seeds;

    public static void main(String[] args) {        
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        int i = Integer.parseInt(args[2]);
        int n = Integer.parseInt(args[3]);
        int d = Integer.parseInt(args[4]);
        
        double[][] data = DataFileReader.ReadeCSVFile(new File(filename));
        
        randomSeeds(k, n);  //This will eventually be replaced by k-means++
        
        double[][]centroids = KMeans.kmeans(data, k, i, seeds);
        
        System.out.println("Clustering complete. Final centroids:");
        
        for (int j = 0; j < centroids.length; j++) {
            StringBuilder line = new StringBuilder();
            line.append("Centroid ");
            line.append(j);
            line.append(": ");
            
            for (int m = 0; m < centroids[0].length; m++) {
                line.append("\t");
                line.append(centroids[j][m]);
            }
            
            System.out.println(line.toString());
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
            boolean uniqueIndex = true;
            Random rand = new Random();
            int index = rand.nextInt(n);
            
            for (int i = 0; i < count; i++) {
                if (index == seeds[i]) {
                    uniqueIndex = false;
                }
            }
            
            if(uniqueIndex) {
                seeds[count] = index;
                count++;
            }
        }
    }
}