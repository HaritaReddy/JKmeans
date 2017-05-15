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

public class KMeans {
    
    private static Cluster[] clusters;
    private static int K;
    private static int I;
    private static int N;
    private static int D;
    private static double[][] data;
    
    /**
     * Performs k-means clustering on data set, provided K,I, and seed positions.
     * 
     * @param dat   Array of data point coordinate, expressed as doubles.
     * @param k     Number of clusters in the data set.
     * @param iter  Number of iterations to run k-means on data set
     * @param seeds List of point indices to be used as seeds for initial centroids.
     * @return 
     */
    public static double[][] kmeans(double[][] dat, int k, int iter, int[] seeds) {
        K = k;
        I = iter;
        N = dat.length;
        D = dat[0].length;
        data = dat;
        clusters = new Cluster[K];
        double[][] centroids = new double[K][D];
        
        for (int i = 0; i < K; i++) {
            clusters[i] = new Cluster(data[seeds[i]]);
        }
        
        for (int i = 0; i < I; i++) {
            ClusterPoints();
            System.out.println("\nIteration " + (i + 1) + " complete...");
        }
        
        for (int i = 0; i < K; i++) {
            centroids[i] = clusters[i].Centroid();
        }
        
        return centroids;
    }
    
    /**
     * Clusters all data points into their nearest centroid's cluster.
     * 
     */
    private static void ClusterPoints() {
        //Assign points to nearest cluster
        for (int i = 0; i < N; i++) {
            if (data[i] == null) {
                System.out.println("There is no point...");
            }
            
            clusters[findNearestCentroid(data[i])].Insert(data[i]);
        }
        
        //Calculate new centroid for each cluster
        for (int i = 0; i < K; i++) {
            clusters[i].CalcCentroid();
        }
        
        //Print out SSE's to monitor progress
        double total = 0.;
        
        for (int i = 0; i < K; i++) {
            double sse = clusters[i].SumSquareError();
            
            total += sse;
            System.out.println("The SSE for cluster " + (i + 1) + " is " + sse);
        }
        
        System.out.println("The total SSE for this iteration: " + total);
        
        //Clear the points in all clusters
        for (int i = 0; i < K; i++) {
            clusters[i].ClearPoints();
        }
    }
    
    /**
     * Calculates the nearest centroid of the candidate point, returns the cluster's index.
     * 
     * @param candidate List of coordinates of a point, expressed as doubles.
     * @return Index of cluster with nearest centroid.
     */
    private static int findNearestCentroid(double[] candidate) {
        int pos = 0;
        double minDist = Distance.Euclidean(candidate, clusters[pos].Centroid());
        
        for (int i = 1; i < clusters.length; i++) {
            if (Distance.Euclidean(candidate, clusters[i].Centroid()) < minDist) {
                pos = i;
                minDist = Distance.Euclidean(candidate, clusters[pos].Centroid());
            }
        }
        
        return pos;
    }    
}
