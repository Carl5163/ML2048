package cwahler.ML2048;

import java.util.ArrayList;
import java.util.List;

public class Network {
    List<Layer> layers;
    NeuralNetParams nParams;

    public Network(NeuralNetParams nParams) {

        this.nParams = nParams;

        layers = new ArrayList<>();
        Layer t = new Layer(1, nParams.numInputs);
        layers.add(t);
        for(int i = 1; i < nParams.numLayers-1; i++) {
            layers.add(new Layer(nParams.numInputs, nParams.neuronsPerHiddenLayer));
        }
        layers.add(new Layer(nParams.neuronsPerHiddenLayer, nParams.numOutputs));
    }

    public List<Double> update(List<Double> inputs) throws NeuralNetworkException {
        if(inputs.size() != nParams.numInputs) {
            throw new NeuralNetworkException(String.format("Number of neuron inputs: %d, does not match number of inputs given: %d.", nParams.numInputs, inputs.size()));
        }

        //Input Layer
        for(int lay = 0; lay < layers.size(); lay++) {
            inputs = layers.get(lay).update(inputs);
            List<Double> temp = new ArrayList<>(inputs);
            if(lay < layers.size()-1) {
                for (int i = 1; i < layers.get(lay + 1).numNeurons; i++) {
                    temp.addAll(inputs);
                }
            } else {
                for (int i = 1; i < nParams.numOutputs; i++) {
                    temp.addAll(inputs);
                }
            }
            inputs = temp;
        }


        return inputs;
    }

    public void print() {
        System.out.printf("Network Description: numLayers = %d\n", nParams.numLayers);
        for(Layer l : layers) {
            l.print();
        }
    }

    public int getNumWeights() {
        return nParams.numInputs+nParams.numInputs*nParams.neuronsPerHiddenLayer*(nParams.numLayers-2)+nParams.neuronsPerHiddenLayer*nParams.numOutputs;
    }

    public int getNumInputs() {
        return nParams.numInputs;
    }

}
