/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jkmeans;

/**
 *
 * @author robert
 */
public class Distance {
    
    public static double Euclidean(double[] a, double[] b) {
        double sum = 0.;
        
        for (int i = 0; i < a.length; i++) {
            double diff = Math.abs(a[i] - b[i]);
            sum += (diff * diff);
        }
        
        return Math.sqrt(sum);
    }
    
    //Hamming distance between 2 Strings
    public static int Hamming(String t1, String t2) {
        if (t1.length() == 0 || t2.length() == 0) {
            throw new IllegalArgumentException("You are trying to measure at least one null string!");
        }
        
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
