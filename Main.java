import java.io.File;
import java.io.IOException;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        NeuralNetwork network = new NeuralNetwork();
        File eight = new File("eight.jpg");

        for (int i = 0; i < 10000; i++) {
            network.train(eight, new int[]{0,0,0,0,0,0,0,0,1,0});
        }

        double[] outputs = network.feedforward(eight);

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
}