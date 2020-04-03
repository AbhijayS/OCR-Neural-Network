import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import org.ejml.simple.SimpleMatrix;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.*;

class NeuralNetwork {
    private final int WIDTH = 28;
    private final int HEIGHT = 28;
    private final int INPUTS = WIDTH*HEIGHT;
    private final int HIDDEN = 15;
    private final int OUTPUTS = 10;
    private final double LR = 0.1;

    SimpleMatrix inputMatrix;
    SimpleMatrix[] weightMatrices;
    SimpleMatrix[] biasMatrices;
    SimpleMatrix[] outputMatrices;

    public NeuralNetwork() {
        weightMatrices = new SimpleMatrix[2];
        biasMatrices = new SimpleMatrix[2];
        outputMatrices = new SimpleMatrix[2];
        
        
        weightMatrices[0] = SimpleMatrix.random_DDRM(HIDDEN, INPUTS, -1, 1, new Random());
        weightMatrices[1] = SimpleMatrix.random_DDRM(OUTPUTS, HIDDEN, -1, 1, new Random());
        biasMatrices[0] = new SimpleMatrix(HIDDEN, 1);
        biasMatrices[1] = new SimpleMatrix(OUTPUTS, 1);
    }

    /**
     * Computes the output for the given input using the feedforward algorithm
     * for each layer in the neural network.
     * output = sigmoid (weights*input + bias)     
     * @param imageArray color representation of the 28x28 pixel image in a row-wise format
     * @return probability values for all numbers between 0-9 inclusive
     */
    public double[] feedforward(Color[] imageArray) {
        inputMatrix = new SimpleMatrix(INPUTS,1);

        for (int i = 0; i < imageArray.length; i++) {
            double value = scale(greyscale(imageArray[i].getRGB()), 0, 255, 0, 1);
            inputMatrix.set(i, value);
        }

        System.out.println(inputMatrix);
 
        outputMatrices[0] = (weightMatrices[0].mult(inputMatrix)).plus(biasMatrices[0]);        //Set the product of weightMatrices[0] and inputMatrix[0] plus biasMatrices[0] of hidden layer to outputMatrices[0]

        for (int i = 0; i < outputMatrices[0].numRows(); i++) {                                 //For loop that loops over outputMatrices[0]
            double value = sigmoid(outputMatrices[0].get(i,0));                                 //Sets the sigmoid of the value of hidden layer output matrix to the double value.
            outputMatrices[0].set(i,0,value);                                                   //New value of sigmoid function set back into hidden layer output matrix
        }

        outputMatrices[1] = (weightMatrices[1].mult(outputMatrices[0])).plus(biasMatrices[1]);  //Set the product of weightMatrices[1] and inputMatrix[1] plus biasMatrices[1] of hidden layer to outputMatrices[1]

        for (int i = 0; i < outputMatrices[1].numRows(); i++) {                                 //For loop that loops over outputMatrices[1]
            double value = sigmoid(outputMatrices[1].get(i,0));                                 //Sets the sigmoid of the value of output layer matrix to the double value.
            outputMatrices[1].set(i,0,value);                                                   //New value of sigmoid function set back into output layer matrix
        }

        double[] outputArray = new double[OUTPUTS];                                             //Create outputArray of double array type with size OUTPUTS

        for (int i = 0; i < OUTPUTS; i++) {                                                     //For loop that loops over OUTPUTS
            outputArray[i] = outputMatrices[1].get(i,0);                                        //Get the output layer matrix values and set them at each outputArray index
        }

        return outputArray;                                                                     //Return outputArray
    }


    /*
     * This method adjusts the weights and biases based on the input and answers
     * using the backpropagation algorithm, otherwise known as gradient descent.
     * @param input 28x28 pixel image file representing the input data
     * @param answers boolean representing numbers 0-9
     */
    public void train(Color[] imageArray, int[] answers) throws IOException {
        feedforward(imageArray);

        /* convert the answers array to a 1D matrix */
        double[][] inArray = new double[answers.length][1];
        for(int i = 0; i < answers.length; i++) {
            inArray[i][0] = answers[i];
        }
        SimpleMatrix answersMatrix = new SimpleMatrix(inArray);

        SimpleMatrix[] errorMatrices = new SimpleMatrix[2];
        
        /*
         * calculate and store errors in the output layer
         * outputError = output - answer
         */
        errorMatrices[1] = outputMatrices[1].minus(answersMatrix);

        /*
         * calculate and store errors in the hidden layer
         * based on the error in the output layer
         * hiddenError = transpose(weights) * outputError
         */
        errorMatrices[0] = (weightMatrices[1].transpose()).mult(errorMatrices[1]);

        /*
         * errorMatrices[0] = errorMatrices[0] ⊙ dsigmoid(outputMatrices[0])
         * errorMatrices[1] = errorMatrices[1] ⊙ dsigmoid(outputMatrices[1])
         */
        SimpleMatrix copyMatrixZero = outputMatrices[0].copy();
        SimpleMatrix copyMatrixOne = outputMatrices[1].copy();
        for(int i = 0; i < copyMatrixZero.numRows(); i++) {
            copyMatrixZero.set(i,0,dsigmoid(copyMatrixZero.get(i,0)));
        }
        for(int i = 0; i < copyMatrixOne.numRows(); i++) {
            copyMatrixOne.set(i,0,dsigmoid(copyMatrixOne.get(i,0)));
        }
        errorMatrices[0] = errorMatrices[0].elementMult(copyMatrixZero);
        errorMatrices[1] = errorMatrices[1].elementMult(copyMatrixOne);

        /* modify the biases */
        biasMatrices[0] = biasMatrices[0].minus(errorMatrices[0]);
        biasMatrices[1] = biasMatrices[1].minus(errorMatrices[1]);
        
        /*
        * define weight deltas according to the rule:
        * deltaWeight = error * transpose(input)
        */
        SimpleMatrix[] deltaWeightMatrices = new SimpleMatrix[2];
        deltaWeightMatrices[0] = errorMatrices[0].mult(inputMatrix.transpose());
        deltaWeightMatrices[1] = errorMatrices[1].mult(outputMatrices[0].transpose());

        /* multiply all deltas by the learning rate */
        for(int i = 0; i < deltaWeightMatrices[0].numRows(); i++) {
            for(int j = 0; j < deltaWeightMatrices[0].numCols(); j++) {
                double value = deltaWeightMatrices[0].get(i,j) * LR;
                deltaWeightMatrices[0].set(i,j,value);
            }
        }
        for(int i = 0; i < deltaWeightMatrices[1].numRows(); i++) {
            for(int j = 0; j < deltaWeightMatrices[1].numCols(); j++) {
                double value = deltaWeightMatrices[1].get(i,j) * LR;
                deltaWeightMatrices[1].set(i,j,value);
            }
        }

        /* modify the weights */
        weightMatrices[0] = weightMatrices[0].minus(deltaWeightMatrices[0]);
        weightMatrices[1] = weightMatrices[1].minus(deltaWeightMatrices[1]);
    }

    /*
     * Sigmoid Function
     * Normalizes all real numbers to within 0 and 1
     */
    private double sigmoid(double value) {
        return 1/(1 + Math.exp(-value));
    }

    /*
     * Special derivative of the sigmoid function
     */
    private double dsigmoid(double value) {
        return value * (1-value);
    }

    /*
     * @return a value between 0 and 1 representing the brightness of a pixel
     * (p>>16) & 0xff - red
     * (p >> 8) & 0xff - green
     * (p) & 0xff - blue
     * ( (0.3 * R) + (0.59 * G) + (0.11 * B) )
     */
    private double greyscale(int RGB) {
        int p = RGB;
        int r = (p>>16)&0xff;
        int g = (p>>8)&0xff;
        int b = p&0xff;
        
        //calculate average grayscale number
        double avg = (0.3 * r) + (0.59 * g) + (0.11 * b);
        return avg;
    }

    /*
     * Scales a value in one range to another
     * 0 - 255
     * 0 - 1
     * x1 0 x2 255 y1 0 y2 1
      */

    private double scale(double value, double x1, double x2, double y1, double y2) {
        double ratio = ((value-x1) / (x2-x1));
        double result = ((y2-y1)*ratio) + y1;
        return result;
    }
}