package java.ru.mirea;

import java.util.Arrays;

public class Main {
    /** You may init and run algorithm here */
    public static void main(String args[]){

        int POPULATION_SIZE = 50;

        System.out.println("Result is: " + Arrays.toString(GeneticAlg.execute(POPULATION_SIZE, doubles -> {
            double result = 0;
            for (double elem: doubles){
                result += elem*elem;
            }
            return result;
        }))); // Now we are calling opt for x^2+y^2+...
    }
}
