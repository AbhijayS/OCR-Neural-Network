import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.*;

import javax.imageio.ImageIO;

public class MNIST {
    public static void main(String[] args) throws Exception {
        File file = new File("train-images.idx3-ubyte");
        InputStream inputStream = new FileInputStream(file);
        ByteBuffer buffer = ByteBuffer.wrap(inputStream.readAllBytes());

        // int image[][] = new int[28][28];
        // int start = 16;
        // for (int i = 0; i < 28; i++) {
        // for (int j = 0; j < 28; j++) {
        // image[i][j] = buffer.get(start) & 0xFF;
        // System.out.print((image[i][j]>128 ? 1 : 0) + " ");
        // start++;
        // }
        // System.out.println();
        // }

        BufferedImage image = new BufferedImage(28,28,BufferedImage.TYPE_INT_RGB);
        int start = 16;
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                int a = buffer.get(start) & 0xFF;
                Color color = new Color(a,a,a);
                image.setRGB(j, i, color.getRGB());
                start++;
            }
        }
        File output = new File("GrayScale.jpg");
        ImageIO.write(image, "jpg", output);
    }
}