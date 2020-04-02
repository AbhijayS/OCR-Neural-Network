import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Scanner;

public class MNIST {
    public static void main(String[] args) throws Exception {
        File file = new File("train-images.idx3-ubyte");
        InputStream inputStream = new FileInputStream(file);
        ByteBuffer buffer = ByteBuffer.wrap(inputStream.readAllBytes());
        System.out.println(buffer.getInt(8));
        System.out.println(buffer.getInt(12));

        byte image[][] = new byte[28][28];
        int start = 16;
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                image[i][j] = buffer.get(start);
                System.out.print(image[i][j] + " ");
                start++;
            }
            System.out.println();
        }
    }
}