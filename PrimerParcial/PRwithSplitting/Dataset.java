import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Dataset {
    private final double[] x;
    private final double[] y;

    public Dataset(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public Dataset[] split(double trainRatio) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < x.length; i++) indices.add(i);
        Collections.shuffle(indices);
        
        int splitIndex = (int) (x.length * trainRatio);
        return new Dataset[]{
            createSubset(indices.subList(0, splitIndex)),
            createSubset(indices.subList(splitIndex, x.length))
        };
    }

    private Dataset createSubset(List<Integer> indices) {
        double[] newX = new double[indices.size()];
        double[] newY = new double[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            int idx = indices.get(i);
            newX[i] = x[idx];
            newY[i] = y[idx];
        }
        return new Dataset(newX, newY);
    }

    public double[] getX() { return x; }
    public double[] getY() { return y; }
}