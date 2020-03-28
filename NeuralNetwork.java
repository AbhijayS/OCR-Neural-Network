import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class NeuralNetwork {
    public static void main(String[] args) throws IOException{
        
        File file = new File("eight.jpg");
        BufferedImage img = ImageIO.read(file);
        int width = img.getWidth();
        int height = img.getHeight();
        System.out.println(width + " " + height);
        System.out.println( (img.getRGB(0, 0)>>8) & 0xff);
        /*
            (p >> 8) & 0xff - green
            (p>>16) & 0xff - red
            (p) & 0xff - blue
            ( (0.3 * R) + (0.59 * G) + (0.11 * B) ).
        */

        // System.out.println(scale(20, 0, 255, 0, 1));
    }
    
    private final int INPUTS = 28*28;
    private final int HIDDEN = 15;
    private final int OUTPUT = 10;
    
    // Todo: import the ejml matrix library
    // Todo: test the library and learn how to use it

    // input_array(I1, I2, I3, ...)
    // weight_matrix_1 - HIDDEN X INPUTS
    /*    I1  I2  I3 ...
     * H1 W11 W12 W13...
     * H2 W21 W22 W23...
     * H3 W31 W32 W33...
     * ...
     */
    // hidden_bias_array

    // hidden_array(H1, H2, H3, ...) - result of (weight_matrix_1)*(input_array)+(hidden_bias_array) using matrix math
    // weight_matrix_2 - OUTPUT X HIDDEN
    // output_bias_array
    // output_array (O1, O2, O3, ...) - result of (weight_matrix_2)*(hidden_array)+(output_bias_array) using matrix math

    public NeuralNetwork() {
        // randomly initialize weight_matrix_1, weight_matrix_2, hidden_bias_array, output_bias_array
    }

    /*
     * feedforward() function
     * computes the output for the given input
     * uses the matrix library to compute the values for the hidden_array
     * uses the hidden_array to compute values for the output
     */

    /*
     * train() function
     * use backpropagation to tune the weights and bias matrices
     * details coming soon
     */
    
     /*
      * train the neural network using MNIST data
      */

    /*
     * Sigmoid Function
     * Normalizes all real numbers to within 0 and 1
     */
    private double sigmoid(double value) {
        return 1/(1 + Math.exp(-value));
    }

    private double greyscale() {
        return 0;
    }

    /*
     * Scales a value in one range to another
     */
    private double scale(double value, double x1, double x2, double y1, double y2) {
        double ratio = ((value-x1) / (x2-x1));
        double result = ((y2-y1)*ratio) + y1;
        return result;
    }
}