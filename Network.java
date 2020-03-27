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
<<<<<<< HEAD
=======
        System.out.println( (img.getRGB(0, 0)>>8) & 0xff);
        /*
            (p >> 8) & 0xff - green
            (p>>16) & 0xff - red
            (p) & 0xff - blue
            ( (0.3 * R) + (0.59 * G) + (0.11 * B) ).
        */

>>>>>>> 690b1c223ddcfa937b285dc606ff575b1e3279bf
        
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
}