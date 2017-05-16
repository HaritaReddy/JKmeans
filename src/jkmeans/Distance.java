package jkmeans;

/**
 * This class holds a collection of distance measures.
 *
 * @author Robert Streetman
 */
public class Distance {
    
    /**
     * Calculates and returns Euclidean distance between two points of equal arbitrary dimensions.
     * @param a Point 1 coordinates.
     * @param b Point 2 coordinates.
     * @return Distance between 1 & 2, expressed as a double.
     */
    public static double Euclidean(double[] a, double[] b) {
        double sum = 0.;
        
        for (int i = 0; i < a.length; i++) {
            double diff = Math.abs(a[i] - b[i]);
            sum += (diff * diff);
        }
        
        return Math.sqrt(sum);
    }
    
    /**
     * Calculates and returns Hamming distance between two strings of arbitrary lengths.
     * 
     * @param t1    String 1
     * @param t2    String 2
     * @return Distance between Strings 1 & 2, expressed as an integer.
     */
    public static int Hamming(String t1, String t2) {
        int length = t1.length();
        int diff;
        
        if (length == t2.length()) {
            diff = 0;
            
            for (int i = 0; i < length; i++) {
                if (t1.charAt(i) == t2.charAt(i)) {
                    diff++;
                }
            }
        } else {
            diff = -1;
        }
        
        return diff;
    }    
}