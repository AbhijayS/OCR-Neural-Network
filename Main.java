import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

class Main {
    static final int IMAGE_SIZE = 784;
    static final int IMAGE_START_INDEX = 16;
    static final int LABEL_START_INDEX = 8;
    public static void main(String[] args) throws Exception {
        NeuralNetwork network = new NeuralNetwork();
        InputStream imageFileInputStream = new FileInputStream(new File("train-images.idx3-ubyte"));
        InputStream labelFileInputStream = new FileInputStream(new File("train-labels.idx1-ubyte"));
        ByteBuffer imageByteBuffer = ByteBuffer.wrap(imageFileInputStream.readAllBytes());
        ByteBuffer labelByteBuffer = ByteBuffer.wrap(labelFileInputStream.readAllBytes());

        int IMAGE_ID = 0;
        int NUM_IMAGES = 5000;

        outputImage(imageByteBuffer, IMAGE_ID, "five");
        System.out.println(labelByteBuffer.get(LABEL_START_INDEX+IMAGE_ID)&0xFF);

        for (int i = 0; i < NUM_IMAGES; i++) {
            // create the answers array for every label
            int[] answersArray = new int[10];
            // get the image from buffer
            Color[] imageArray = getImageFromBuffer(imageByteBuffer, i);
            
            network.train(getImageFromBuffer(imageByteBuffer, IMAGE_ID), new int[] {0,0,0,0,0,1,0,0,0,0});
            IMAGE_ID++;
        }

        double[] outputs = network.feedforward(getImageFromBuffer(imageByteBuffer, IMAGE_ID));

        int index = 0;
        double max = 0;
        for (int i = 0; i < outputs.length; i++) {
            System.out.printf("%.4f ", outputs[i]);
            if (outputs[i] > max) {
                max = outputs[i];
                index = i;
            }
        }
        System.out.println("\nIt's " + (index));
    }

    static void outputImage(ByteBuffer buffer, int imageIndex, String fileName) throws Exception {
        Color[] imageArray = getImageFromBuffer(buffer, imageIndex);
        int[] rgb = new int[imageArray.length];
        for (int i = 0; i < imageArray.length; i++) {
            rgb[i] = imageArray[i].getRGB();
        }
        BufferedImage image = new BufferedImage(28,28,BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, 28, 28, rgb, 0, 28);
        File output = new File(fileName+".jpg");
        ImageIO.write(image, "jpg", output);
    }

    static Color[] getImageFromBuffer(ByteBuffer buffer, int imageIndex) {
        Color[] image = new Color[IMAGE_SIZE];
        for (int i = 0; i < IMAGE_SIZE; i++) {
            int c = buffer.get((imageIndex*IMAGE_SIZE)+IMAGE_START_INDEX+i)&0xFF;
            image[i] = new Color(c, c, c);
        }
        return image;
    }

    static int[] getAnswersArrayFromBuffer(ByteBuffer buffer, int imageIndex) {
        int[] answersArray = new int[10];
        for(int i = 0; i < 10; i++) {
            answersArray[i] = buffer.get(imageIndex+LABEL_START_INDEX+i);
        }
        return answersArray;
    }
}