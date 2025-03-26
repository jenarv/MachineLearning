import java.text.DecimalFormat;

class PolynomialRegression extends RegressionModel {
    private final int degree;
    private double[] coefficients;

    public PolynomialRegression(int degree) {
        this.degree = degree;
    }

    @Override
    public void train(Dataset dataset) {
        double[][] X = createDesignMatrix(dataset.getX());
        double[] y = dataset.getY();
        
        double[][] Xt = MathUtils.transpose(X);
        double[][] XtX = MathUtils.matrixMultiply(Xt, X);
        double[][] XtXInv = MathUtils.invertMatrix(XtX);
        double[][] XtXInvXt = MathUtils.matrixMultiply(XtXInv, Xt);
        this.coefficients = MathUtils.matrixVectorMultiply(XtXInvXt, y);
    }

    private double[][] createDesignMatrix(double[] x) {
        double[][] matrix = new double[x.length][degree + 1];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j <= degree; j++) {
                matrix[i][j] = Math.pow(x[i], j);
            }
        }
        return matrix;
    }

    @Override
    public double predict(double x) {
        double prediction = 0;
        for (int i = 0; i <= degree; i++) {
            prediction += coefficients[i] * Math.pow(x, i);
        }
        return prediction;
    }

    public String getEquation() {
        StringBuilder equation = new StringBuilder("y = ");
        DecimalFormat df = new DecimalFormat("0.0000");
        for (int i = 0; i <= degree; i++) {
            String term = df.format(coefficients[i]);
            if (i > 0) term += "x" + (i > 1 ? "^" + i : "");
            equation.append(i == 0 ? term : " + " + term);
        }
        return equation.toString();
    }

    public double[] getCoefficients() {
        return coefficients;
    }
}