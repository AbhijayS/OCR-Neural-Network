import java.io.File;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        NeuralNetwork network = new NeuralNetwork();
        
        double[] output = network.feedforward(new File("eight.jpg"));

        for (double d : output) {
            System.out.println(d);
        }
        
        network.train(new int[]{0,0,0,0,0,0,0,0,1,0});
    }
}