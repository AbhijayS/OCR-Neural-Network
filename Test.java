import org.ejml.equation.Equation;
import org.ejml.simple.SimpleMatrix;

class Test {
    public static void main(String[] args) {
        Equation equation = new Equation();
        SimpleMatrix matrix = new SimpleMatrix(new double[][]{
            {2, 4},
            {3, 5}
        });

        equation.alias(matrix, "M");
        equation.process("M(:)=2");
        equation.print("M(:)=2");
    }
}