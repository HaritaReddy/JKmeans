/**
 * @author Robert Streetman
 * @date 2014-12-21
 * @desc The k-means clustering algorithm is a prototype-based (considers "ideal" points) partitioning
 *      algorithm designed to take a data set of N points, all with dimensionality D, and separate
 *      them into K clusters for I iterations. Generally, the first points used as prototypes for the 
 *      clusters are randomly chosen from the data set. The size and shape of the initial clusters may 
 *      fluctuate wildly at first, but rapidly converge to a constant value (within 3-5 iterations).
 *      To ensure it is working properly, at each iteration, the variance of the points in each cluster
 *      is determined and summed together. While the variance of an individual cluster may increase in
 *      early iterations as new points are added, the sum of all cluster SSE will decline if working 
 *      properly (i.e., each point has a centroid nearer to it than the iteration before).
 */
package jkmeans;

import distance.Distance;
import java.util.ArrayList;

public class Kmeans {
    
    private static Cluster[] clusters;
    private static int K;
    private static int I;
    private static int N;
    private static int D;
    private static double[][] data;
    
    /*
     * The default case reuires the user to provide a data set of N D-dimensional points, the number of clusters
     * K, and a list of pres-selected seed indices. The random seeds are calculated externally because eventually,
     * a k-means++ class will look at the data points and mathematically select the best starting points.
     */
    public static double[][] kmeans(double[][] dat, int k, int iter, int[] seeds) {
        K = k;
        I = iter;
        N = dat.length;
        D = dat[0].length;
        data = dat;
        clusters = new Cluster[K];
        
        //Some standard parameter checking
        if(K <= 0) {
            throw new IllegalArgumentException("There must be at least one cluster!");
        }
        if(I < 5) {
            throw new IllegalArgumentException("Kmeans should run at least 5 iterations to stabilize!");
        }
        if(N <= 0 || N <= K) {
            throw new IllegalArgumentException("The data set has too few points!");
        }
        if(D <= 0) {
            throw new IllegalArgumentException("The data must have at least one dimension!");
        }
        
        for(int i = 0; i < K; i++) {
            clusters[i] = new Cluster(data[seeds[i]]);
        }
        for(int i = 0; i < I; i++) {
            ClusterPoints();
            System.out.println("Iteration " + (i + 1) + " complete...");
        }
        
        double[][] centroids = new double[K][D]; 
        for(int i = 0; i < K; i++) {
            System.arraycopy(clusters[i].Centroid(), 0, centroids[i], 0, D);
        }
        return centroids;
    }
    
    /*
     * Once all the clusters have been assigned an inital centroid, each point looks at a list
     * of centroids to determine the nearest one. When all points have been sorted, the current
     * centroid i discarded and a new one calculated as the mean value of each dimension of 
     * each point in the cluster. The list of points in each centroid is purged and the process
     * resumes again.
     */
    private static void ClusterPoints() {
        //Assign points to nearest cluster
        for(int i = 0; i < N; i++) {
            if(data[i] == null) {
                System.out.println("There is no point...");
            }
            clusters[findNearestCentroid(data[i])].Insert(data[i]);
        }
        //Calculate new centroid for each cluster
        for(int i = 0; i < K; i++) {
            clusters[i].CalcCentroid();
        }
        //Print out SSE's to monitor progress
        double total = 0.;
        for(int i = 0; i < K; i++) {
            double sse = clusters[i].SumSquareError();
            total += sse;
            System.out.println("The SSE for cluster " + (i + 1) + " is " + sse);
        }
        System.out.println("The total SSE for this iteration: " + total);
        //Clear the points in all clusters
        for(int i = 0; i < K; i++) {
            clusters[i].ClearPoints();
        }
    }
    
    /*
     * A quick external measuring/minimum-seeking function
     */
    private static int findNearestCentroid(double[] candidate) {
        int pos = 0;
        double minDist = Distance.Euclidean(candidate, clusters[pos].Centroid());
        for(int i = 1; i < clusters.length; i++) {
            if(Distance.Euclidean(candidate, clusters[i].Centroid()) < minDist) {
                pos = i;
                minDist = Distance.Euclidean(candidate, clusters[pos].Centroid());
            }
        }
        return pos;
    }
    
}
