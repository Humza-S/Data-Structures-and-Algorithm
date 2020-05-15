/*
  Author: Humza Salman
  Class: CS 3345.004
  Description: The algorithm in this code is the "Sieve of Erotosthenes". It is used to compute 
  all prime numbers less than a given integer (which has to be greater than 1).
*/

import java.util.Scanner;
import java.util.Arrays;

class Project1 {
  public static void main(final String[] args) {
    
    // get the input from user and d store it
    Scanner input = new Scanner(System.in);
    System.out.print("Input an integer greater than 1: ");
    int inputNumber = input.nextInt();

    // creating a boolean array to length of the input number and initializing it to true
    boolean[] boolArray = new boolean[inputNumber];
    Arrays.fill(boolArray, true);

    // Sieve of Erotosthenes algorithm - description can be found in Project 1 pdf
    for(int i = 2; i < Math.sqrt(inputNumber); i++){
      if(boolArray[i] == true) {
        for(int j = i*i; j < inputNumber; j += i) {
          boolArray[j] = false;
        }
      }
    }

    // printing the prime numbers less than the given input number
    for(int i = 2; i < boolArray.length; i++) {
      if(boolArray[i]) { // if the value in the array is true, then it is a prime number
        System.out.println(i);
      }
    }
    input.close();
  }
}