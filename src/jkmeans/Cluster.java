package jkmeans;

import java.util.ArrayList;

/**
 * This class holds representation of a data cluster.
 * 
 * @author Robert Streetman
 */
public class Cluster {
    
    private ArrayList<double[]> points;
    private double[] centroid;
    private int D;
    
    /**
     * Cluster instantiation.
     * 
     * @param c A double array representing the coordinates of the cluster centroid (average). 
     */
    public Cluster(double[] c) {
        D = c.length;
        /*
        if (D == 0) {
            throw new IllegalArgumentException("The centroid cannot be a null point!");
        }*/
        
        centroid = new double[c.length];        //The first point is automatically the centroid until it is explicitly re-calculated
        System.arraycopy(c, 0, centroid, 0, c.length);  //This is supposed to be the fastest method of copying arrays, but I have not tested it myself
        points = new ArrayList<>();
    }
    
    /**
     * Retrieve centroid (average) of the cluster as a double[] of coordinate values.
     * 
     * @return Cluster centroid.
     */
    public double[] Centroid() {
        return centroid;
    }
    
    /**
     * Set cluster centroid with coordinates of given centroid.
     * 
     * @param c Array of coordinates expressed as double.
     */
    public void SetCentroid(double[] c) {
        /*if (c.length == 0) {
            throw new IllegalArgumentException("The centroid cannot be a null point!");
        }*/
        
        centroid = new double[c.length];
        System.arraycopy(c, 0, centroid, 0, c.length);
    }
    
    /**
     * Insert given point into centroid.
     * 
     * @param point Array of point coordinates expressed as double[].
     */
    public void Insert(double[] point) {
        /*if (point.length == 0) {
            throw new IllegalArgumentException("You cannot add a null point!");
        }
        
        if (points == null) {
            throw new IllegalArgumentException("This arraylist is empty!");
        }*/
        
        double[] pt = new double[point.length];
        System.arraycopy(point, 0, pt, 0, point.length);
        points.add(pt);
    }
    
    /**
     * Returns the sum of squared errors. The lower this value, the more compact the cluster. This value
     * should decrease as clustering progresses, until reaching a local min.
     * 
     * @return The sum of the squared distance from each point to the centroid.
     */
    public double SumSquareError() {
        double sse = 0.;
        
        for (double[] point : points) {
            double dist = Distance.Euclidean(point, centroid);
            sse += dist * dist;
        }
        
        return sse;
    }
    
    /**
     * Clears points in the cluster before each iteration.
     */
    public void ClearPoints() {
        points.clear();
    }
    
    /**
     * Calculates and stores the value of the new centroid from all points which have been added to the cluster.
     */
    public void CalcCentroid() {
        /*
        if (D == 0) {
            throw new IllegalArgumentException("You cannot cluster null points!");
        }
        */
        double[] sum = new double[D];
        
        int n = 0;
        
        for (double[] point : points) {
            for (int i = 0; i < D; i++) {
                sum[i] += point[i];
            }
            
            n++;
        }
        
        for (int i = 0; i < D; i++) {
            sum[i] /= n;
        }
        
        System.arraycopy(sum, 0, centroid, 0, D);
    }
}