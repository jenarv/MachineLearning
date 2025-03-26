class Metrics {

    // coefficient of determination (how predictions match actual values)
    public static double rSquared(double[] yTrue, double[] yPred) {
        double yMean = MathUtils.mean(yTrue);
        double ssTotal = 0;
        double ssResidual = 0;
        
        // calculate the sums
        for(int i=0; i<yTrue.length; i++) {
            ssTotal += Math.pow(yTrue[i] - yMean, 2); //variance of the actual value
            ssResidual += Math.pow(yTrue[i] - yPred[i], 2); // squared error of predictions
        }
        return 1 - (ssResidual / ssTotal);
    }

    //calculates the pearson correlation coefficient between x and y
    public static double correlation(double[] x, double[] y) {
        if(x.length != y.length) throw new IllegalArgumentException("Arrays deben tener misma longitud");
        
        double sumXY = MathUtils.dotProduct(x, y);
        double sumX = MathUtils.sum(x);
        double sumY = MathUtils.sum(y);
        double sumXSq = MathUtils.dotProduct(x, x); //sum of squares of X
        double sumYSq = MathUtils.dotProduct(y, y); //sum of squares of y
        int n = x.length;
        
        double numerator = n * sumXY - sumX * sumY;
        double denominator = Math.sqrt(
            (n * sumXSq - sumX * sumX) * 
            (n * sumYSq - sumY * sumY)
        );
        
        return numerator / denominator;
    }
}