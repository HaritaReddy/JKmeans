package jkmeans;
/**********************************************************************************************************************************************
Robert Streetman
LSUS - CST 790 (Dr Celebi)
Fall 2012
Class: Normalize
Description: This class holds functions for normalizing data ( ArrayList< double[] > dataPoints ) in several methods.
Included is Min-Max, Z-Score, ...
**********************************************************************************************************************************************/
import java.util.ArrayList;

/**
 * Class holding methods for normalizing data.
 * 
 * @author Robert Streetman, 2012
 */
public class Normalize {
	
    /**
     * MinMax normalization method normalizes each dimension of each point based on min/max of that dimension across all points.
     * @param data Array of data point coordinates, expressed as doubles.
     * @return Array of normalized data points, expressed as doubles.
     */
    public static double[][] minMax(double[][] data) {
        int points = data.length;
        int dimen = data[0].length;
        double[] min;
        double[] max;
        double[][] norm = new double[points][dimen];

        //Set the min and max of each attribute to the first point in the data set
        min = max = data[0];
        
        //Find the highest and lowest value of each attribute in the set
        for (int p = 0; p < points; p++) {
            for (int d = 0; d < dimen; d++) {
                //New min value for dimension?
                if (data[p][d] < min[d]) {
                    min[d] = data[p][d];
                }
                
                //New max value for dimension?
                if (data[p][d] > max[d]) {
                    max[d] = data[p][d];
                }
            }
        }
        
        for (int p = 0; p < points; p++) {            
            for (int d = 0; d < dimen; d++) {
                if(min[d] == max[d]) {
                    norm[p][d] = 0.;
                } else {
                    norm[p][d] = (data[p][d] - min[d]) / (max[d] - min[d]);
                }
            }
        }
        
        return norm;
    }

    /**
     * MaxNorm normalization method normalizes data points based on the max value of all the points' dimensions.
     * 
     * @param data Array of data point coordinates, expressed as doubles.
     * @return Array of normalized data points, expressed as doubles.
     */
    public static double[][] maxNorm(double[][] data ) {
        int points = data.length;
        int dimen = data[0].length;
        //Set the max of each attribute to the first point in the set
        double[] max = data[0];
        double[][] norm = new double[points][dimen];	//List of normalized data points
        
        //Find the highest value of each attribute in the set
        for (int p = 0; p < points; p++) {
            for (int d = 0; d < dimen; d++) {
                if (data[p][d] > max[d]) {
                    max[d] = data[p][d];
                }
            }
        }
        
        for (int p = 0; p < points; p++) {
            for (int d = 0; d < dimen; d++) {
                if (max[d] == 0.) {
                    norm[p][d] = 0.;
                } else {
                    norm[p][d] = data[p][d] / max[d];
                }
            }
        }
        
        return norm;
    }

    /**
     * RangeNorm normalization method normalizes data points based on the range of all the points' dimensions.
     * 
     * @param data Array of data point coordinates, expressed as doubles.
     * @return Array of normalized data points, expressed as doubles.
     */
    public static ArrayList<double[]> rangeNorm(ArrayList<double[]> data) {
        int dimen = data.get(0).length;
        double[] min = new double[dimen];		//List of the lowest value in each attribute
        double[] max = new double[dimen];		//List of the highest value in each attribute
        
        //Set the min and max of each attribute to the first point in the set
        for (int a = 0; a < dimen; a++) {
            min[a] = max[a] = data.get(0)[a];
        }
        
        //Find the highest and lowest value of each attribute in the set
        for (double[] pt : data) {
            for (int a = 0; a < dimen; a++) {
                //See if the min-max values need to be adjusted
                if (pt[a] < min[a]) {
                    min[a] = pt[a];
                }
                
                if (pt[a] > max[a]) {
                    max[a] = pt[a];
                }
            }
        }
        
        //Create normalized list of data points by dividing attr. value by attr. range
        ArrayList<double[]> norm = new ArrayList();	//List of normalized data points
        
        for (double[] pt : data) {
            double[] newPoint = new double[dimen];
            
            for (int a = 0; a < dimen; a++) {
                if (min[a] == max[a]) {
                    newPoint[a] = 0.;
                } else {
                    newPoint[a] = pt[a] / (max[a] - min[a]);
                }
            }
            
            norm.add(newPoint.clone());
        }
        
        return norm;
    }

    /**
     * ZScore normalization method normalizes data points based on the z-score of each dimension.
     * 
     * @param data Array of data point coordinates, expressed as doubles.
     * @return Array of normalized data points, expressed as doubles.
     */
    public static ArrayList<double[]> zScore(ArrayList<double[]> data) {
        int dimen = data.get(0).length;
        int N = data.size();
        double[] mean = new double[dimen];		//List of the mean value of each attribute
        double[] sums = new double[dimen];		//List of the sum of values of each attribute
        double[] stdDev = new double[dimen];		//List of the standard deviation of each attribute
        double[] sumSquared = new double[dimen];	//List of the variance of each attribute
        
        //Find the sum of all values for each attribute in the data set
        for (double[] pt : data) {
            for (int a = 0; a < dimen; a++) {
                sums[a] += pt[a];
            }
        }
        
        //Calculate the mean value of each attribute in the data set
        for (int a = 0; a < dimen; a++) {
            mean[a] = sums[a] / N;
        }
        
        //Find the variance of each attribute in the data set
        for (double[] pt : data) {
            for (int a = 0; a < dimen; a++) {
                sumSquared[a] += (pt[a] - mean[a]) * (pt[a] - mean[a]);
            }
        }
        
        //Calculate standard deviation of each attribute in the data set
        for (int a = 0; a < dimen; a++) {
            stdDev[a] = Math.sqrt(sumSquared[a] / (N - 1));
        }
        
        //Create normalized list of data points by subtracting attr. mean from attr. value, then dividing by attr. std. dev.
        ArrayList<double[]> norm = new ArrayList();	//List of normalized data points
        
        for (double[] pt : data) {
            double[] newPoint = new double[dimen];
            
            for (int a = 0; a < dimen; a++) {
                if (stdDev[a] == 0.) {
                    newPoint[a] = 0.;
                } else {
                    newPoint[a] = (pt[a] - mean[a]) / stdDev[a];
                }
            }
            
            norm.add(newPoint.clone());
        }
        
        return norm;
    }

    /**
     * EuclidNorm normalization method normalizes data points based on the Euclidean norm of all the points' dimensions.
     * 
     * @param data Array of data point coordinates, expressed as doubles.
     * @return Array of normalized data points, expressed as doubles.
     */
    public static ArrayList<double[]> euclidNorm(ArrayList<double[]> data) {
        int dimen = data.get(0).length;
        double[] euclidNorm = new double [dimen];			//List of the Euclid. norm of each attribute
        
        //Calculate the Euclidean norm of each attribute
        for (int a = 0; a < dimen; a++) {
            double sumSquare = 0.;
            
            for (double[] pt : data) {
                sumSquare += pt[a] * pt[a];
            }
            
            euclidNorm[a] = Math.sqrt(sumSquare);
        }
        
        //Create the normalized list of data points by dividing attr. value by attr. Euclid. norm
        ArrayList<double[]> norm = new ArrayList();	//List of normalized data points
        
        for (double[] pt : data) {
            double[] newPoint = new double[dimen];
            
            for (int a = 0; a < dimen; a++) {
                if (euclidNorm[a] == 0.) {
                    newPoint[a] = 0.;
                } else {
                    newPoint[a] = pt[a] / euclidNorm[a];
                }
            }
            
            norm.add(newPoint.clone());
        }
        
        return norm;
    }

    /**
     * RankedNorm normalization method normalizes data points based on the ranking of all the points.
     * 
     * @param data Array of data point coordinates, expressed as doubles.
     * @return Array of normalized data points, expressed as doubles.
     */
    public static ArrayList<double[]> rankedNorm(ArrayList<double[]> data ) {
        double[] tmp = data.get(0);
        int dimen = tmp.length;
        int size = data.size();
        double[][] rankings = new double[dimen][size];	//List of rankings for each attr. value of each point in data set
        
        //Seed the rankings table with the values from the first point in the data set
        for (int a = 0; a < dimen; a++) {
            rankings[a][0] = tmp[a];
        }
        
        //Rank the data with an insertion sort. This code will fill out a D x N table of double values, in ascending order.
        for (int p = 1; p < size; p++) {
            tmp = data.get(p);
            
            for (int a = 0; a < dimen; a++) {
                //
                //Use insertion sort to put attribute in its place. Based on pseudocode in Intro to Algorithms 3rd Ed. (Cormen, et al.) p.18
                //
                double key = tmp[a];	//The value to be inserted
                int i = p - 1;			//This corresponds to 'j - 1'.
                
                while(i >= 0 && rankings[ a ][ i ] > key) {
                    rankings[a][i + 1] = rankings[a][i];	//Move this element up one rank
                    i--;											//Decrement the index
                }
                
                rankings[a][i + 1] = key;	//Set the key in its new correct position
            }
        }
        
        //Create list of normalized points by finding the rank of every one of a point's attr. values
        ArrayList<double[]> norm = new ArrayList();	//List of normalized data points
        
        for (int p = 0; p < size; p++) {
            tmp = data.get(p);
            double[] newPoint = new double[dimen];
            int rank;						//Lowest rank with this value
            int maxRank;					//Highest rank with this value
            
            for (int a = 0; a < dimen; a++) {
                rank = 0;		//Lowest index with matching value
                maxRank = 0;	//Highest index with matching value
                
                //Find the lowest rank matching that attribute value
                while(rank < size && tmp[a] > rankings[a][rank]) {
                    rank++;
                    maxRank++;
                }
                
                while(maxRank < size && tmp[a] == rankings[a][maxRank]) {
                    maxRank++;
                }
                
                //If only one ranking has that value, return the ranking
                if(maxRank == rank) {
                    newPoint[ a ] = rank;
                //If more than one ranking has that value, sum the value of rankings and divide by number
                } else {
                    double sum = 0.;	//Sum the rankings
                    int count = 0;		//Count how many rankings there are with this value
                    
                    for (int i = 0; i <  (maxRank - rank); i++) {
                        sum += (rank + i);
                        count++;
                    }
                    
                    newPoint[a] = sum / count;
                }
            }
            
            norm.add(newPoint.clone());
        }
        
        return norm;
    }
}