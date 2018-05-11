package cwahler.ML2048;

import java.util.ArrayList;
import java.util.List;

public class Layer {

    int numInputs;
    int numNeurons;
    List<Neuron> neurons;

    public Layer(int numInputs, int numNeurons) {

        this.numInputs = numInputs;
        this.numNeurons = numNeurons;

        neurons = new ArrayList<>();
        for(int i = 0; i < numNeurons; i++) {
            neurons.add(new Neuron(numInputs));
        }
    }

    public List<Double> update(List<Double> inputs) throws NeuralNetworkException {

        List<Double> ret = new ArrayList<>();

        if(inputs.size() != numInputs*numNeurons) {
            throw new NeuralNetworkException(String.format("Number of neuron inputs: %d, does not match number of inputs given: %d.", numInputs*numNeurons, inputs.size()));
        }

        for(int i = 0; i < neurons.size(); i++) {
            ret.add(neurons.get(i).update(inputs.subList(i*numInputs, (i+1)*numInputs), 1));
        }

        return ret;
    }

    public void print() {
        System.out.printf("Layer Description: numNeurons = %d, numInputs = %d\n", numNeurons, numInputs);
        for(Neuron n : neurons) {
            n.print();
        }
    }


}
