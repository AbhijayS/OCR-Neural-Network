import org.ejml.data.Matrix;
import org.ejml.simple.SimpleMatrix;

class MatrixTester {
    public static void main(String[] args) {
        /*
        2 4  5
        1 3  6

        2*5 + 4*6 = 34
        1*5 + 3*6 = 23
         */

        float[][] data1 = {
            {2, 4},
            {1, 3}
        };

        float[][] data2 = {
            {5},
            {6}
        };

        SimpleMatrix m1 = new SimpleMatrix(data1);
        SimpleMatrix m2 = new SimpleMatrix(data2);

        SimpleMatrix result = m1.mult(m2);

        System.out.println(result);
    }
}