package Math;

import java.util.List;
import java.util.ArrayList;

/**
 * All about prime numbers
 * 
 * @author FLAG
 */

public class Prime {
	public boolean isPrime(int n) {
		if(n<=1) return false;
		for(int i=2; i<=n/i; i++) {
			if(n%i==0 && n/i>1) return false;
		}
		return true;
	}
	
	public List<Integer> listPrimes(int n) {
		List<Integer> list = new ArrayList<>();
		if(n<2) return list;
        boolean[] notPrime = new boolean[n];
		for(int i=2, nSqrt=(int)Math.sqrt(n); i<=nSqrt; i++) {
			if(!notPrime[i]) {
				for(int j=2; j*i<n; j++) {
					notPrime[i*j] = true;
				}
			}
		}
		for(int i=2; i<n; i++) {
			if(!notPrime[i]) list.add(i);
		}
		return list;
	}	
	
	public int countPrimes(int n) {
		return listPrimes(n).size();
	}
	
	public static void main(String[] args) {
		Prime p = new Prime();
		
		int n = 10000;

		System.out.println("primes<="+n+": " + p.listPrimes(n));
		System.out.println("count primes: " + p.countPrimes(n));
	}
}
