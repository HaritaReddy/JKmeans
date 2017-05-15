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
        /*
        if (t1.length() == 0 || t2.length() == 0) {
            throw new IllegalArgumentException("You are trying to measure at least one null string!");
        }
        */
        
        int shorter = (t1.length() > t2.length()) ? t1.length() : t2.length();
        int diff = (t1.length() == t2.length()) ? 0 : Math.abs(t2.length() - t1.length());
        
        for (int i = 0; i < (shorter - 1); i++) {
            if (Character.compare(t1.charAt(i), t2.charAt(i)) != 0) {
                diff++;
            }
        }
        
        return diff;
    }
    
}