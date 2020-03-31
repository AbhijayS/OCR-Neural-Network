import org.ejml.simple.SimpleMatrix;

public class elementMult{
    public static void main(String[] args) {
        SimpleMatrix newMatrix = new SimpleMatrix(new double[][]{{2,4},{3,5}});
        System.out.println(newMatrix);
        SimpleMatrix finalMatrix = newMatrix.elementMult(newMatrix);
        System.out.println(finalMatrix);

    }
}