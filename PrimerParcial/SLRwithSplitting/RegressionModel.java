public abstract class RegressionModel {
    public abstract void train(Dataset dataset);
    public abstract double predict(double x);
    
    public double calculateRSquared(Dataset dataset) {
        double[] x = dataset.getX();
        double[] y = dataset.getY();
        double[] predictions = new double[y.length];
        for(int i=0; i<y.length; i++) predictions[i] = predict(x[i]);
        return Metrics.rSquared(y, predictions);
    }
}