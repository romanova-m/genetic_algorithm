package ru.mirea;

class GeneticAlg {

    /** Holds optimized function */
    private static FuncInterface func;
    /** Just to reduce code. Calls evaluate for given args **/
    static double f(double... args){return func.evaluate(args);}

    /**
     * Finding best solution.
     * Size of population and optimized function on input
     * Array of best arguments on output
     *
     * @param populationSize number of required individuals in population.
     * @param func wrapper object for function calculation
     * @return array at which the function is minimal
     */
    static double[] execute(int populationSize, FuncInterface func){
        GeneticAlg.func = func;
        int generationCount = 0;

        Population population = new Population(populationSize);
        population.individuals =  population.createRandomPop();

        Individual lastFittest = population.getFittest();
        Individual currFittest = population.getFittest();
        System.out.println( "Generation: " + generationCount + " Best F: " +
                (-1) * population.getFittest().getFitness());
        for (int i = 0; i < 100; i++){
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
