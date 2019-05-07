package ru.mirea;

class GeneticAlg {

    /** Constants important for algorithm */
    private static final int iterationsNumber = 100;
    static final double uniformRate = 0.8;
    static final double mutationRate = 0.01;
    static final int tournamentSize = 10;
    private static final int populationSize = 300;

    /** Holds optimized function */
    private static FuncInterface func;
    /** Just to reduce code. Calls evaluate for given args **/
    static double f(double... args){return func.evaluate(args);}

    /**
     * Finding best solution.
     * Size of population and optimized function on input
     * Array of best arguments on output
     *
     * @param func wrapper object for function calculation
     * @return array at which the function is minimal
     */
    static double[] execute(FuncInterface func){
        GeneticAlg.func = func;
        int generationCount = 0;

        Population population = new Population(populationSize);
        population.individuals =  population.createRandomPop();

        Individual lastFittest = population.getFittest();
        Individual currFittest = population.getFittest();
        System.out.println( "Generation: " + generationCount + " Best F: " +
                (-1) * population.getFittest().getFitness());
        for (int i = 0; i < iterationsNumber; i++){
            lastFittest = currFittest;
            population.evolvePopulation();
            currFittest = population.getFittest();
            generationCount++;
             System.out.println( "Generation: " + generationCount + " Best F: " +
                     (-1) * population.getFittest().getFitness());
        }
        return lastFittest.getGeneDoubles();
    }
}
