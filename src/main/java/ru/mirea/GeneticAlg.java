package java.ru.mirea;

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

        Individual lastFittest = population.getFittest();
        Individual currFittest = population.getFittest();


        while (lastFittest.getFitness() < currFittest.getFitness()) {
            System.out.println( "Generation: " + generationCount + " Correct genes found: " +
                    population.getFittest().getFitness());
            population = evolvePopulation(population);
            generationCount++;
        }

        return lastFittest.getCords();
    }

    private static Population evolvePopulation(Population population) {
        return population;
    }
}
