/**
 * @author Robert Streetman
 * @date 2014-12-21
 * @desc I created a data structure since a cluster is more of a full object rather than a simple data type.
 *      Each Cluster keeps track of several variables as well as its own list of data points, along
 *      with methods to modify them.
 */
package jkmeans;

import distance.Distance;
import java.util.ArrayList;

public class Cluster {
    
    private ArrayList<double[]> points;
    private double[] centroid;
    private int D;
    
    /*
     * The double that is an input parameter represents a numerical data point with D attributes (x, y, z,..).
     * This is used in initialization to learn parameters required for other operations.
     */
    public Cluster(double[] c) {
        D = c.length;
        if(D == 0) {
            throw new IllegalArgumentException("The centroid cannot be a null point!");
        }
        centroid = new double[c.length];        //The first point is automatically the centroid until it is explicitly re-calculated
        System.arraycopy(c, 0, centroid, 0, c.length);  //This is supposed to be the fastest method of copying arrays, but I have not tested it myself
        points = new ArrayList<>();
    }
    
    /*
     * Returns the current centroid, which is passed to an external distance function in k-means, in order to sort points.
     * Also useful for verification of results.
     */
    public double[] Centroid() {
        return centroid;
    }
    
    /*
     * I like getter/setter methods because they offer a simple mechanism to help ensure that one class isn't inadvertantly
     * interfering with the internal variables of another class. By making the variables private, modification must be explicit
     * and parameters must be in a prescribed format.
     */
    public void SetCentroid(double[] c) {
        if(c.length == 0) {
            throw new IllegalArgumentException("The centroid cannot be a null point!");
        }
        centroid = new double[c.length];
        System.arraycopy(c, 0, centroid, 0, c.length);
    }
    
    /*
     * Offers a controlled avenue for adding an Object to a list. An extra layer of error-proofing, really
     */
    public void Insert(double[] point) {
        if(point.length == 0) {
            throw new IllegalArgumentException("You cannot add a null point!");
        }
        if(points == null) {
            throw new IllegalArgumentException("This arraylist is empty!");
        }
        double[] pt = new double[point.length];
        System.arraycopy(point, 0, pt, 0, point.length);
        points.add(pt);
    }
    
    /*
     * The core metric of the k-means algorithm. For each point in the cluster, the distance from that
     * point to the centroid (which usually will not exist in the cluster, see k-medoid is squared,
     * and all the distances summed. The lower this value, the more tightly-packed a cluster because
     * the points are better represented by the prototype. K-means is a minima-seeking algorithm, so
     * the value should usually decrease on each iteration until it plateaus, after which it should 
     * definitely NOT increase again. The performance of a clustering algorithm is based on the sum
     * of the SSE from each of the K clusters.
     */
    public double SumSquareError() {
        double sse = 0.;
        for(double[] point : points) {
            double dist = Distance.Euclidean(point, centroid);
            //I did this so the Euclidean distance measure is only done once. It finds the square root of a number which is very expensive
            sse += dist * dist;     //I didn't use Math.pow() for the same reason: the power never changes, and I only have to write the name twice
        }
        return sse;
    }
    
    /*
     * Handy for quickly clearing out the current points before each iteration
     */
    public void ClearPoints() {
        points.clear();
    }
    
    /*
     * The centroid of a cluster is simply the average values of all the points in the cluster - a prototype.
     * When initialized, it will be the same value as the point it is given, but it will likely never be an
     * actual member of the data set after that. The k-means++ algorithm is focused on intelligently seeding
     * this value, and some proposed methods may not use a data set point as a seed at all.
     */
    public void CalcCentroid() {
        if(D == 0) {
            throw new IllegalArgumentException("You cannot cluster null points!");
        }
        double[] sum = new double[D];
        
        //Used counter variable because an ArrayList is initialized with some 10 slots even if there aren't that many objects to hold
        int n = 0;
        for(double[] point : points) {
            for(int i = 0; i < D; i++) {
                sum[i] += point[i];
            }
            n++;
        }
        for(int i = 0; i < D; i++) {
            sum[i] /= n;
        }
        System.arraycopy(sum, 0, centroid, 0, D);
    }
}
