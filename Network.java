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
        
    }
}