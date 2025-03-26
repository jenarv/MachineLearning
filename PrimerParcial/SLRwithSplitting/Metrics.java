public class Metrics {
    public static double rSquared(double[] yTrue, double[] yPred) {
        double yMean = ArrayMath.mean(yTrue);
        double ssTotal = 0;
        double ssResidual = 0;
        
        for(int i=0; i<yTrue.length; i++) {
            ssTotal += Math.pow(yTrue[i] - yMean, 2);
            ssResidual += Math.pow(yTrue[i] - yPred[i], 2);
        }
        return 1 - (ssResidual / ssTotal);
    }

    public static double correlation(double[] x, double[] y) {
        double sumXY = ArrayMath.dotProduct(x, y);
        double sumX = ArrayMath.sum(x);
        double sumY = ArrayMath.sum(y);
        double sumX2 = ArrayMath.sumSquares(x);
        double sumY2 = ArrayMath.sumSquares(y);
        int n = x.length;
        
        double numerator = n * sumXY - sumX * sumY;
        double denominator = Math.sqrt((n * sumX2 - sumX*sumX) * (n * sumY2 - sumY*sumY));
        
        return numerator / denominator;
    }
}