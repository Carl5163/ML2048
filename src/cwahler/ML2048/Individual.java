package cwahler.ML2048;

import java.util.List;

public class Individual {

    private Network net;

    public Individual(Network net) {
       this.net = net;
    }

    public void print() {
       net.print();
    }

    public int getNumInputs() {
           return net.getNumInputs();
    }
    public List<Double> update(List<Double> inputs) throws NeuralNetworkException{
        return net.update(inputs);
    }

}
