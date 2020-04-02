import java.io.File;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        NeuralNetwork network = new NeuralNetwork();
        File eight = new File("eight.jpg");

        network.train(eight, new int[]{0,0,0,0,0,0,0,0,1,0});
        network.train(eight, new int[]{0,0,0,0,0,0,0,0,1,0});
        network.train(eight, new int[]{0,0,0,0,0,0,0,0,1,0});
        network.train(eight, new int[]{0,0,0,0,0,0,0,0,1,0});
        network.train(eight, new int[]{0,0,0,0,0,0,0,0,1,0});
        network.train(eight, new int[]{0,0,0,0,0,0,0,0,1,0});
        
        double[] outputs = network.feedforward(eight);
    }
}