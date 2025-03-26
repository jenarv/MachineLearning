public class ArrayMath {
    public static double sum(double[] array) {
        double total = 0;
        for(double num : array) total += num;
        return total;
    }

    public static double dotProduct(double[] a, double[] b) {
        if(a.length != b.length) throw new IllegalArgumentException("Arrays deben tener misma longitud");
        double result = 0;
        for(int i=0; i<a.length; i++) result += a[i] * b[i];
        return result;
    }

    public static double sumSquares(double[] array) {
        return dotProduct(array, array);
    }

    public static double mean(double[] array) {
        return sum(array) / array.length;
    }
}