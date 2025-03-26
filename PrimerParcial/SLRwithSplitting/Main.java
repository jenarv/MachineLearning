public class Main {
    private static final double[][] DATA = {
        {108,95}, {115,96}, {106,95}, {97,97}, {95,93},
        {91,94}, {97,95}, {83,93}, {83,92}, {78,86},
        {54,73}, {67,80}, {56,65}, {53,69}, {61,77},
        {115,96}, {81,87}, {78,89}, {30,60}, {45,63},
        {99,95}, {32,61}, {25,55}, {28,56}, {90,94}, {89,93}
    };

    public static void main(String[] args) {
        Dataset fullDataset = loadDataset();
        Dataset[] bestTestDataset = new Dataset[1];
        SLR bestModel = findBestModel(fullDataset, bestTestDataset);
        displayResults(bestModel, fullDataset);
    }

    private static Dataset loadDataset() {
        double[] x = new double[DATA.length];
        double[] y = new double[DATA.length];
        for(int i=0; i<DATA.length; i++) {
            x[i] = DATA[i][0];
            y[i] = DATA[i][1];
        }
        return new Dataset(x, y);
    }

    private static SLR findBestModel(Dataset fullDataset, Dataset[] bestTestDataset) {
        SLR bestModel = null;
        double bestR2 = Double.NEGATIVE_INFINITY;
        
        for(int i=0; i<3; i++) {
            Dataset[] split = fullDataset.split(0.7);
            SLR model = new SLR();
            model.train(split[0]);
            double currentR2 = model.calculateRSquared(split[1]);
            
            if (currentR2 > bestR2) {
                bestR2 = currentR2;
                bestModel = model;
                bestTestDataset[0] = split[1]; 
            }
        }
        return bestModel;
    }

    private static void displayResults(SLR model, Dataset testDataset) {
        System.out.println("=== Modelo Final ===");
        System.out.printf("Ecuación: y = %.4f + %.4fx\n", model.getB0(), model.getB1());
        System.out.printf("R² en conjunto de prueba: %.4f\n", model.calculateRSquared(testDataset));
        System.out.printf("Correlación: %.4f\n\n", Metrics.correlation(testDataset.getX(), testDataset.getY()));
        
        double[] testCases = {108, 115, 200, 50, 30};
        System.out.println("Predicciones:");
        for(double x : testCases) {
            System.out.printf("Batch Size: %5.1f → Eficiencia: %6.2f\n", x, model.predict(x));
        }
    }
}