import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.ejml.simple.SimpleMatrix;
import java.io.File;
import java.io.IOException;
import java.util.Random;

class NeuralNetwork {
    private final int WIDTH = 28;
    private final int HEIGHT = 28;
    private final int INPUTS = WIDTH*HEIGHT;
    private final int HIDDEN = 15;
    private final int OUTPUTS = 10;

    SimpleMatrix inputMatrix;
    SimpleMatrix answersMatrix;
    SimpleMatrix[] weightMatrices;
    SimpleMatrix[] biasMatrices;
    SimpleMatrix[] errorMatrices;

    public NeuralNetwork() {
        Random rand = new Random();

        weightMatrices[0] = SimpleMatrix.random_DDRM(HIDDEN, INPUTS, -1, 1, rand);
        weightMatrices[1] = SimpleMatrix.random_DDRM(OUTPUTS, HIDDEN, -1, 1, rand);
        biasMatrices[0] = SimpleMatrix.random_DDRM(HIDDEN, 1, -1, 1, rand);
        biasMatrices[1] = SimpleMatrix.random_DDRM(OUTPUTS, 1, -1, 1, rand);
    }

    /*
     * feedforward() function
     * computes the output for the given input
     * uses the matrix library to compute the values for the hidden_array
     * uses the hidden_array to compute values for the output
     */
    public double[] feedforward(File file) throws IOException {
        BufferedImage img = ImageIO.read(file);
        double[][] inputArray = new double[INPUTS][1];

        int count = 0;
        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++){
                inputArray[count][0] = scale(greyscale(img.getRGB(x, y)), 0, 255, 0, 1);
                count++;
            }
        }

        inputMatrix = new SimpleMatrix(inputArray);
 
        SimpleMatrix hiddenMatrix = (weightMatrices[0].mult(inputMatrix)).plus(biasMatrices[0]);

        for (int i = 0; i < hiddenMatrix.numRows(); i++) {
            double value = sigmoid(hiddenMatrix.get(i,0));
            hiddenMatrix.set(i,0,value);
        }

        SimpleMatrix outputMatrix = (weightMatrices[1].mult(hiddenMatrix)).plus(biasMatrices[1]);

        for (int i = 0; i < outputMatrix.numRows(); i++) {
            double value = sigmoid(outputMatrix.get(i,0));
            outputMatrix.set(i,0,value);
        }

        double[] outputArray = new double[OUTPUTS];

        for (int i = 0; i < OUTPUTS; i++) {
            outputArray[i] = outputMatrix.get(i,0);
        }

        return outputArray;
    }


    /*
     * train() function
     * use backpropagation to tune the weights and bias matrices
     * details coming soon
     */
    
     /*
      * train the neural network using MNIST data
      */

    public void train(int[] answers) {

        // calculate final errors



        // calculate hidden errors
        errorMatrices[0] = weightMatrices[1].transpose().mult(errorMatrices[1]);
    }

    /*
     * Sigmoid Function
     * Normalizes all real numbers to within 0 and 1
     */
    private double sigmoid(double value) {
        return 1/(1 + Math.exp(-value));
    }

    /*
     * @return a value between 0 and 1 representing the brightness of a pixel
     * (p>>16) & 0xff - red
     * (p >> 8) & 0xff - green
     * (p) & 0xff - blue
     * ( (0.3 * R) + (0.59 * G) + (0.11 * B) )
     */
    private double greyscale(int RGB) {
        int p = RGB;
        int r = (p>>16)&0xff;
        int g = (p>>8)&0xff;
        int b = p&0xff;
        
        //calculate average grayscale number
        double avg = (0.3 * r) + (0.59 * g) + (0.11 * b);
        return avg;
    }

    /*
     * Scales a value in one range to another
     * 0 - 255
     * 0 - 1
     * x1 0 x2 255 y1 0 y2 1
      */

    private double scale(double value, double x1, double x2, double y1, double y2) {
        double ratio = ((value-x1) / (x2-x1));
        double result = ((y2-y1)*ratio) + y1;
        return result;
    }
}