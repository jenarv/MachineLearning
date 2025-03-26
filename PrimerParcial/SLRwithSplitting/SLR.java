public class SLR extends RegressionModel {
    private double b0;
    private double b1;

    @Override
    public void train(Dataset dataset) {
        double[] x = dataset.getX();
        double[] y = dataset.getY();
        int n = x.length;
        
        double sumX = ArrayMath.sum(x);
        double sumY = ArrayMath.sum(y);
        double sumXY = ArrayMath.dotProduct(x, y);
        double sumX2 = ArrayMath.sumSquares(x);
        
        this.b1 = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        this.b0 = (sumY - b1 * sumX) / n;
    }

    @Override
    public double predict(double x) {
        return b0 + b1 * x;
    }
    
    public double getB0() { return b0; }
    public double getB1() { return b1; }
}