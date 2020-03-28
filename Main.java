import java.io.File;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        NeuralNetwork network = new NeuralNetwork();
        
        network.feedforward(new File("eight.jpg"));

        // network.feedforward(img)
        // print(network.output())

        // network.train()

    }
}