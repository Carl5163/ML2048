package cwahler.ML2048;

import java.util.ArrayList;
import java.util.List;

public class Population {

    private List<Individual> pop;
    private NeuralNetParams nParams;
    private GeneticAlgParams gParams;

    public Population(NeuralNetParams nParams, GeneticAlgParams gParams) {

        this.nParams = nParams;
        this.gParams = gParams;
        pop = new ArrayList<>();
        for(int i = 0; i < gParams.popSize; i++) {
            pop.add(new Individual(new Network(nParams)));
        }
    }

    public void print(int index) {
        if(index < pop.size()) {
            pop.get(index).print();
        }
    }

    public List<List<Double>> update(List<Double> inputs) throws NeuralNetworkException{
        List<List<Double>> ret = new ArrayList<>();
        if(inputs.size() != gParams.popSize*nParams.numInputs) {
            throw new NeuralNetworkException(String.format("Number of neuron inputs: %d, does not match number of inputs given: %d.", (gParams.popSize*pop.get(0).getNumInputs()), inputs.size()));
        }

        for(int i = 0; i < pop.size(); i++) {
            ret.add(pop.get(i).update(inputs.subList(i*nParams.numInputs,(i+1)*nParams.numInputs)));
        }
        return ret;
    }
}
