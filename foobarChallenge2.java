package foobarChallenge2;
import java.util.Arrays;
import java.math.BigInteger;

/*
Foobar Question 2

Find the largest positive product of a given list of integers (positive and negative)
Two negative integers can be multiplied together to create a positive.  


Solution

-Write function solution(xs)
    -takes list(array) of integers
    -integers represent power output levels of each panel in array
    -returns max product subset of those numbers (non-empty)
        -iterate through array, get product of all numbers in array
        -ignore 0's
        -if next number is positive, product = product*product
        -if next number is negative, check next number + 1 for negative
            -if both negative, multiply into product
            -if one negative, continue

-Each panel contains at least 1, no more than 50 panels (numbers)
    -each panel's |power| is < 1000
        -(absolute value of power value)
-2 negative panels within a set produce a positive output

-Convert final product to string representation for solution
-Use BigInteger to account for size restrictions of integers 


*/


class foobarChallenge2 {
    public static String solution(int[] xs) {
        
        if(xs.length == 1)
            return Integer.toString(xs[0]); 

        BigInteger product = new BigInteger("1");
        Arrays.sort(xs); //sort low to high

        int j = xs.length-1;
        while(xs[j] == 0){
            j--;
        }
        
        product = BigInteger.valueOf(xs[j]);


        for(int i = 0; i < j ; i++) {

            if(xs[i] == 0) {
                continue;
            }
            //if negative
            else if(xs[i] < 0) {
                //if next is also negative
                if(i + 1 < xs.length && xs[i+1] < 0) {
                    product = product.multiply(BigInteger.valueOf(xs[i] * xs[i+1]));
                    i++;
                    continue;
                }
            }
            //if positive    
            else if(xs[i] > 0) {
                product = product.multiply(BigInteger.valueOf(xs[i]));
            }   
        }
        
        if(product.intValue() < 0) {
            return Integer.toString(0);
        }
        return product.toString();
    }

    public static void main(String[] args) {
        
        int[] panels = new int[] {-1,0};
        String solutionString = solution(panels);
        System.out.print(solutionString);

    }

}