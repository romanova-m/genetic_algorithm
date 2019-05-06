package java.ru.mirea;

/**
 * Represents elementary population member
 */
class Individual {
    /* Set of doubles as a way to represent genes*/
    private double[] x;

    Individual(double[] x) {
        this.x = x;
    }

    double getFitness() {
        return GeneticAlg.f(x);
    }

    double[] getCords() {
        return x;
    }
}
