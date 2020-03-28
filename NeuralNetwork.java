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
        
        // System.out.println(scale(20, 0, 255, 0, 1));
    }
    
    private final int WIDTH = 28;
    private final int HEIGHT = 28;
    private final int INPUTS = WIDTH*HEIGHT;
    private final int HIDDEN = 15;
    private final int OUTPUT = 10;
    
    // Todo: import the ejml matrix library
    // Todo: test the library and learn how to use it
    // multiply two matrices together
    // add two matrices together

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

    public void feedforward(File file) {
        // input_array
        // for each pixel in the image
        // input_array[x][y] = scale(greyscale(pixel))

        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++){
            }
        }
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

    /*
     * @return a value between 0 and 1 representing the brightness of a pixel
     * (p>>16) & 0xff - red
     * (p >> 8) & 0xff - green
     * (p) & 0xff - blue
     * ( (0.3 * R) + (0.59 * G) + (0.11 * B) )
     */
    private double greyscale(int RGB) {
        int p = RGB;
        int a = (p>>24)&0xff;
        int r = (p>>16)&0xff;
        int g = (p>>8)&0xff;
        int b = p&0xff;
        
        //calculate average grayscale number
        double avg = (0.3 * r) + (0.59 * g) + (0.11 * b)
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