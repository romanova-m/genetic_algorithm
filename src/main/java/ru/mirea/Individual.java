package ru.mirea;

/**
    Elementary population member
 */
class Individual {

    /** Dimensions number */
    private int n = 2;
    /** Chars for one dimension */
    private int len = 64;
    /** Bit representation of vector components */
    private String gene = randomChromosome();
    private int defaultGeneLength = n * len;

    /** Makes object with random genes */
    Individual() {
    }

    double[] getGeneDoubles(){return geneToDoubles(gene);}

    /** Converts gene to array of doubles */
    private double[] geneToDoubles(String gene) {
        double[] result = new double[n];
        int from = 0;
        int to = len;
        for (int i = 0; i < n; i++){
            result[i] = Double.longBitsToDouble(Long.parseUnsignedLong(
                    gene.substring(from, to), 2));
            from += len;
            to += len;
        }
        return result;
    }

    double getFitness() {
        return -1 * GeneticAlg.f(getGeneDoubles()); // inverse relation
    }

    int getDefaultGeneLength(){
        return defaultGeneLength;
    }

    private String randomChromosome(){
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < n; j++) { // for each double
            for (int i = 0; i < (len); i++) {
                result.append(randomGene());
            }
        }
        return result.toString();
    }

    char randomGene() {
        return Double.toString(Math.floor(Math.random()*2)).charAt(0); // Generates "0" or "1"
    }

    char getSingleGene(int i) {
        return gene.charAt(i);
    }

    void setSingleGene(int i, char singleGene) {
        StringBuilder str = new StringBuilder();
        str.append(gene);
        str.replace(i,i+1, "" + singleGene);
        gene = str.toString();
    }
}
