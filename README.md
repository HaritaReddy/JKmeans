# JKmeans
Java implementation of the k-means clustering algorithm. K-means accepts *N* *d*-dimensional data points and groups them into *k* clusters, choosing the nearest cluster based on the distance between the data point and the prototype (*centroid*) of the cluster. Simply, it is a way of grouping a large number of disparate points into a small number of similar groups, then finding the "ideal" member of each group. After grouping, the centroid of each cluster can be updated, and the clustering repeated over *i* iterations. After each iteration, the sum of each cluster's sum of squared errors (SSE) will be lower than the previous iteration. K-means is a simple minimum-seeking algorithm, and will stop at a local minimum, even if a better one exists - therefore the issue of how initial centroids are seeded becomes the most critical.

:warning: K-means is not suited to every data clustering project. Membership is **mutually exclusive** - a point cannot be in more than one cluster at once. If any of the characteristics on which the points are being clustered are not useful in discriminating classes, the final clustering can be affected, so *too much* data can be an issue. :warning:

## Usage
**Command-line:** *-fileName* *-numberClusters* *-numberIterations*

## Data Format
Program assumes the simple tab-separated value format of the UC-Irvine Machine Learning Repository (no headers, no text, uniform dimensionality)

## To-Do
- Additional 'smart' seeding methods (k-means++)
- Additional distance measurements (Manhattan, Mahalanobis, etc.)
- Rand index to measure correlation
- File output/class labelling
- Ability to cluster text data points (**Ex** via Hamming)
  - Ability to cluster data points that are combination of text **and** digits

### Support This Project :+1:
If you wish to help fund further development of this project, feel free to donate to 1G6nykFakoTc6NzzCnDBesGLYUzcvs28vD :tea::coffee::beer: