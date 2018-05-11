package cwahler.ML2048;

import java.util.ArrayList;
import java.util.Random;

public class Neuron {

    private ArrayList<Double> weights;

    public Neuron(int numInputs) {
        weights = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < numInputs; i++) {
            weights.add(random.nextDouble());
        }
    }

    public double update(ArrayList<Double> inputs) {
        return 0;
    }


 }
