package cwahler.ML2048;

import java.util.ArrayList;

public class Population {
    ArrayList<Individual> pop;

    public Population(NeuralNetParams nParams, GeneticAlgParams gParams) {
        pop = new ArrayList<>();
        for(int i = 0; i < gParams.popSize; i++) {
            pop.add(new Individual(new Network(nParams)));
        }
    }
}
