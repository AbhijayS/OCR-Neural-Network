import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class Network {
    public static void main(String[] args) {
        System.out.println("hello world");
        System.out.println(sigmoid(6));
    
    }

    /*
     * Sigmoid Function
     * Normalizes all real numbers to within 0 and 1
     */
    public static double sigmoid(double value) {
        return 1/(1 + Math.exp(-value));
    }
}