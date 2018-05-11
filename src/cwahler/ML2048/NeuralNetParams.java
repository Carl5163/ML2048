package cwahler.ML2048;

public class NeuralNetParams {
    int numLayers ;
    int numInputs ;
    int numOutputs;
    int neuronsPerHiddenLayer;
    int bias;
    public NeuralNetParams(int numLayers, int numInputs, int numOutputs, int neuronsPerHiddenLayer, double bias) {
        this.numLayers = numLayers;
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.neuronsPerHiddenLayer = neuronsPerHiddenLayer;
    }
}
