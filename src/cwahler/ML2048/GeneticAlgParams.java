package cwahler.ML2048;

public class GeneticAlgParams {
    int popSize;
    double crossoverChance;
    double mutationChance;
    double percentTop;
    public GeneticAlgParams(int popSize, double crossoverChance, double mutationChance, double percentTop) {
        this.popSize = popSize;
        this.crossoverChance = crossoverChance;
        this.mutationChance = mutationChance;
        this.percentTop = percentTop;
    }
}
