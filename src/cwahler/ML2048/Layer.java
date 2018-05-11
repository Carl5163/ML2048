package cwahler.ML2048;

import java.util.ArrayList;

public class Layer {
    ArrayList<Neuron> neurons;
    public Layer(int numInputs, int numNeurons) {
        neurons = new ArrayList<>();
        for(int i = 0; i < numNeurons; i++) {
            neurons.add(new Neuron(numInputs));
        }
    }
}
