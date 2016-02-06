package Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Divide two integers using only subtraction, addition and bit operation
 * 
 * @author FLAG
 */
public class Divide {
	
	/**
	 * Key idea: keep doubling divisor till no larger than dividend, then subtract amplified divisor from dividend till dividend is small than original divisor
	 * Example: 121 / 11
	 * 11<<1 --> 22<<1 --> 44<<1 --> 88, stop because 88<<1 = 176 > 121. divisor amplified 3 times, thus add 2<<3=8 to the final result
	 * update dividend with 121-88 = 33 and continue with above procedure:
	 * 11<<1 --> 22<<1, stop because 22<<1 = 44 > 33. final result = 8+2 = 10
	 * update dividend with 33-22 = 11 and continue with above procedure:
	 * stop because 11<<1 = 22 > 11. final result = 10+1 = 11
	 * update dividend with 11-11 = 0 and return
 	 */
    public int divide(int dividend, int divisor) {
    	final int MIN = 0x80000000 ;
    	if(divisor==0) throw new ArithmeticException("/ by zero");
    	if(divisor==-1 && dividend==MIN) throw new ArithmeticException("overflow");
    	
    	int sign = (dividend>=0 && divisor>0 || dividend<=0 && divisor<0) ? 1 : -1; // sign of final result
    	dividend = dividend>0 ? -dividend : dividend; // force dividend to negative
    	divisor = divisor>0 ? -divisor : divisor; // force divisor to negative
    	
    	if(divisor==-1) return sign==1 ? -dividend : dividend; // special case
    	
    	int res = 0; // final result
    	while(dividend<=divisor) { // keep subtracting the largest number (absolute value) that is small than dividend (absolute value) till exhausted
        	int divisorCurrent = divisor;
        	int resCurrent = 1;
	    	while(divisorCurrent>dividend>>1) { // keep doubling divisor till exhausted
	    		divisorCurrent = divisorCurrent<<1;
	    		resCurrent = resCurrent<<1;
	    	}
	    	res += resCurrent; // accumulating final result
	    	dividend -= divisorCurrent; // update dividend
    	} 
    	return sign==1 ? res : -res;
    }
	
	public static void main(String[] args) {
		Divide q = new Divide();

		//int dividend = 2147483647;
		//int divisor = 2;
		//int dividend = 50;
		//int divisor = 3;
		int dividend = 0x7fffffff;
		int divisor = 7919;
		
		long start = System.nanoTime();
		
		int res = q.divide(dividend, divisor);
		
		long end = System.nanoTime();
		double duration = (end-start)/1000000.;

		System.out.println("built-in: " + dividend / divisor);
		System.out.println("coded: " + res);
		
		System.out.println("duration: " + duration + "ms");
	}
}
