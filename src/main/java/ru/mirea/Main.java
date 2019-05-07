package ru.mirea;

import java.util.Arrays;
public class Main {
    /** Runs algorithm here */
    public static void main(String args[]){
        /* Replace with your function */
        FuncInterface funcInterface = doubles -> (11.0/5.0)*doubles[0]*doubles[0] + (6.0/5.0)*doubles[1]*doubles[1] -
                (22.0/10.0)*doubles[1]*doubles[0] + doubles[0];

        double[] result = GeneticAlg.execute(funcInterface);
        System.out.println("Result is: " + Arrays.toString(result) + "\tF=" + funcInterface.evaluate(result));
    }
}
