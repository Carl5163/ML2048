package cwahler.ML2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neuron {

    private List<Double> weights;
    private int numInputs;

    public Neuron(int numInputs) {

        this.numInputs = numInputs;
        weights = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < numInputs; i++) {
            weights.add(random.nextDouble()-random.nextDouble()*2);
        }
    }

    public double update(List<Double> inputs, double bias) throws NeuralNetworkException {
        if(inputs.size() != numInputs) {
            throw new NeuralNetworkException(String.format("Number of neuron inputs: %d, does not match number of inputs given: %d.", numInputs, inputs.size()));
        }

        double sum = bias;

        for(int i = 0; i < inputs.size(); i++) {
            sum += inputs.get(i)*weights.get(i);
        }

        return 1/(1+Math.exp(-1*sum));
    }

    public void print() {
        System.out.printf("Neuron Description: numInputs: %d\n", numInputs);
        for (Double d : weights) {
            System.out.printf("Weight = %f\n", d);
        }
    }

 }
