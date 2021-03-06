package ru.mirea;

import java.util.ArrayList;

class Population {

    private int capacity;
    ArrayList<Individual> individuals = new ArrayList<>();

    Population(int populationSize) {
        this.capacity = populationSize;
    }

    ArrayList<Individual> createRandomPop() {
        ArrayList<Individual> list = new ArrayList<>();
        for (int i = 0; i < capacity; i++){
            list.add(new Individual());
        }
        return list;
    }

    Individual getFittest() {
        Individual result = individuals.get(0);
        for (Individual individual : individuals){
            if (individual.getFitness() > result.getFitness()) result = individual;
        }
        return result;
    }

    void evolvePopulation() {
        ArrayList<Individual> newIndividuals = new ArrayList<>();
        int elitismOffset;
        if (elitism()) {
            newIndividuals.add(0, this.getFittest());
            individuals.remove(this.getFittest());
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        for (int i = elitismOffset; i < capacity; i++)
            newIndividuals.add(i, crossover(tournamentSelection(), tournamentSelection()));
        individuals = newIndividuals;
    }
    private Individual mutate(Individual indiv) {
        for (int i = 0; i < indiv.getDefaultGeneLength(); i++) {
            double mutationRate = GeneticAlg.mutationRate;
            if (Math.random() <= mutationRate) {
                indiv.setSingleGene(i, indiv.randomGene());
            }
        }
        return indiv;
    }

    private Individual tournamentSelection() {
        int tournamentSize = GeneticAlg.tournamentSize;
        Population tournament = new Population(tournamentSize);
        ArrayList<Individual> pool = new ArrayList<>(individuals);

        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pool.size());
            tournament.individuals.add(pool.get(randomId));
            pool.remove(randomId);
        }
        return mutate(tournament.getFittest());
    }

    /**
     * Randomly selects genes from two best individuals.
     * Uniform rate represents probability to gain genes from first person.
     */
    private Individual crossover(Individual ind1, Individual ind2) {
        Individual newSol = new Individual();
        for (int i = 0; i < newSol.getDefaultGeneLength(); i++) {
            double uniformRate = GeneticAlg.uniformRate;
            if (Math.random() <= uniformRate) newSol.setSingleGene(i, ind1.getSingleGene(i));
            else newSol.setSingleGene(i, ind2.getSingleGene(i));
        }
        return newSol;
    }

    private boolean elitism() {
        return  getBestIndividuals().size() == 1;
    }

    private ArrayList<Individual> getBestIndividuals(){
        ArrayList<Individual> result = new ArrayList<>();
        double fitness = this.getFittest().getFitness();
        for(Individual ind: individuals){
            if (ind.getFitness() == fitness) result.add(ind);
        }
        return result;
    }
}
