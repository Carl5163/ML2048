package cwahler.ML2048;

import java.util.ArrayList;

public class Network {
    ArrayList<Layer> layers;
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

}
