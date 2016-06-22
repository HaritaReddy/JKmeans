/**
 * @author Robert Streetman
 * @date 2014-12-19
 * @desc Offers various distance measures for multi-dimensional points of various data types.
 */
package jkmeans;

public class Hamming {

    //Hamming distance between 2 Strings
    public static int Hamming(String t1, String t2) {
        if(t1.length() == 0 || t2.length() == 0) {
            throw new IllegalArgumentException("You are trying to measure at least one null string!");
        }
        int shorter = (t1.length() > t2.length()) ? t1.length() : t2.length();
        int diff = (t1.length() == t2.length()) ? 0 : Math.abs(t2.length() - t1.length());
        for(int i = 0; i < (shorter - 1); i++) {
            if(Character.compare(t1.charAt(i), t2.charAt(i)) != 0) {
                diff++;
            }
        }
        return diff;
    }
    
    //Hamming distance between 2 char arrays
    public static int Hamming(char[] t1, char[] t2) {
        if(t1.length == 0 || t2.length == 0) {
            throw new IllegalArgumentException("You are trying to measure at least one null string!");
        }
        int shorter = (t1.length > t2.length) ? t1.length : t2.length;
        int diff = (t1.length == t2.length) ? 0 : Math.abs(t2.length - t1.length);
        for(int i = 0; i < (shorter - 1); i++) {
            if(Character.compare(t1[i], t2[i]) != 0) {
                diff++;
            }
        }
        return diff;
    }
    
    //Hamming distance between 2 char arrays
    public static int Hamming(String t1, char[] t2) {
        if(t1.length() == 0 || t2.length == 0) {
            throw new IllegalArgumentException("You are trying to measure at least one null string!");
        }
        int shorter = (t1.length() > t2.length) ? t1.length() : t2.length;
        int diff = (t1.length() == t2.length) ? 0 : Math.abs(t2.length - t1.length());
        for(int i = 0; i < (shorter - 1); i++) {
            if(Character.compare(t1.charAt(i), t2[i]) != 0) {
                diff++;
            }
        }
        return diff;
    }
    
    //Hamming distance between 2 char arrays
    public static int Hamming(char[] t1, String t2) {
        if(t1.length == 0 || t2.length() == 0) {
            throw new IllegalArgumentException("You are trying to measure at least one null string!");
        }
        int shorter = (t1.length > t2.length()) ? t1.length : t2.length();
        int diff = (t1.length == t2.length()) ? 0 : Math.abs(t2.length() - t1.length);
        for(int i = 0; i < (shorter - 1); i++) {
            if(Character.compare(t1[i], t2.charAt(i)) != 0) {
                diff++;
            }
        }
        return diff;
    }
