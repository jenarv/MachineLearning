import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Dataset {
    private final double[] x;
    private final double[] y;

    public Dataset(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public Dataset[] split(double trainRatio) {
        // create a list to store the indices of the dataset
        List<Integer> indices = new ArrayList<>();
        for(int i=0; i<x.length; i++) indices.add(i); // add each index to the list
        Collections.shuffle(indices); // shuffle the indices randomly for random splitting
        
        // calcuate the index at which to split
        int splitIndex = (int)(x.length * trainRatio);
        
        //return the training set and test set
        return new Dataset[]{
            createSubset(indices.subList(0, splitIndex)),
            createSubset(indices.subList(splitIndex, x.length))
        };
    }

    private Dataset createSubset(List<Integer> indices) {
        double[] newX = new double[indices.size()];
        double[] newY = new double[indices.size()];

        //populate the new arrays with data at the index
        for(int i=0; i<indices.size(); i++) {
            int idx = indices.get(i);
            newX[i] = x[idx];
            newY[i] = y[idx];
        }
        return new Dataset(newX, newY);
    }

    public double[] getX() { return x; }
    public double[] getY() { return y; }
}