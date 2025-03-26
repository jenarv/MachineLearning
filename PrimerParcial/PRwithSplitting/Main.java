import java.text.DecimalFormat;

public class Main {
    private static final double[][] DATA = {
            { 108, 95 }, { 115, 96 }, { 106, 95 }, { 97, 97 }, { 95, 93 },
            { 91, 94 }, { 97, 95 }, { 83, 93 }, { 83, 92 }, { 78, 86 },
            { 54, 73 }, { 67, 80 }, { 56, 65 }, { 53, 69 }, { 61, 77 },
            { 115, 96 }, { 81, 87 }, { 78, 89 }, { 30, 60 }, { 45, 63 },
            { 99, 95 }, { 32, 61 }, { 25, 55 }, { 28, 56 }, { 90, 94 }, { 89, 93 }
    };

    public static void main(String[] args) {
        Dataset dataset = loadDataset();

        evaluateModel(1, dataset);
        evaluateModel(2, dataset);
        evaluateModel(3, dataset);
    }

    private static Dataset loadDataset() {
        double[] x = new double[DATA.length];
        double[] y = new double[DATA.length];
        for (int i = 0; i < DATA.length; i++) {
            x[i] = DATA[i][0];
            y[i] = DATA[i][1];
        }
        return new Dataset(x, y);
    }

    private static void evaluateModel(int degree, Dataset dataset) {
        PolynomialRegression bestModel = null;
        double bestR2 = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < 3; i++) {
            Dataset[] split = dataset.split(0.7);
            PolynomialRegression model = new PolynomialRegression(degree);
            model.train(split[0]);
            double currentR2 = model.calculateRSquared(split[1]);

            if (currentR2 > bestR2) {
                bestR2 = currentR2;
                bestModel = model;
            }
        }

        System.out.println("\n=== Modelo Polinomial Grado " + degree + " ===");
        System.out.println("Ecuación: " + bestModel.getEquation());
        System.out.printf("Correlación: %.4f\n", Metrics.correlation(dataset.getX(), dataset.getY()));
        System.out.printf("R² en prueba: %.4f\n", bestR2);
        System.out.printf("R² en dataset completo: %.4f\n", bestModel.calculateRSquared(dataset));
        System.out.println("Betas:");
        printCoefficients(bestModel.getCoefficients());
        testPredictions(bestModel);
    }

    private static void printCoefficients(double[] coefficients) {
        DecimalFormat df = new DecimalFormat("0.0000");
        for (int i = 0; i < coefficients.length; i++) {
            System.out.printf("B%d = %s\n", i, df.format(coefficients[i]));
        }
    }

    private static void testPredictions(PolynomialRegression model) {
        double[] testCases = { 108, 115, 97, 50, 30 };
        System.out.println("\nPredicciones:");
        for (double x : testCases) {
            System.out.printf("Batch Size: %5.1f | Eficiencia: %6.2f\n", x, model.predict(x));
        }
        System.out.println("______________________________________");
    }
}