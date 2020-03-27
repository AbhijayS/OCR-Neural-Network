import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class Network {
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

        System.out.println(scale(20, 0, 255, 0, 1));
    }

    /*
     * Sigmoid Function
     * Normalizes all real numbers to within 0 and 1
     */
    public static double sigmoid(double value) {
        return 1/(1 + Math.exp(-value));
    }

    public static double greyscale(){
        return 0;
    }

    /*
     * Scales a value in one range to another
     */
    public static double scale(double value, double x1, double x2, double y1, double y2) {
        double ratio = ((value-x1) / (x2-x1));
        double result = ((y2-y1)*ratio) + y1;
        return result;
    }
}