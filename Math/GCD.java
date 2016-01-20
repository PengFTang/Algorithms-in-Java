/**
 * Implements greatest common divisor (GCD) using Euclidean's method that supports negative inputs
 * 
 * @author Peng F. Tang
 * pengftang@gmail.com
 */
public class GCD {
	
	public int GCD(int a, int b) {
		int shift = 0;
		if(a==0x80000000 && b==0x80000000) return a;
		else if(a==0x80000000) {
			shift = b%2==0 ? 1 : 0;
			a = a>>1;
		}
		else if(b==0x80000000) {
			shift = a%2==0 ? 1 : 0;
			b = b>>1;
		}
		
		a = a<0 ? -a : a; // force a to be positive
		b = b<0 ? -b : b; // force b to be positive
		
		while(a!=0 && b!=0) {
			if(a>=b) {
				a = a%b;
				a = a<0 ? -a : a;
			}
			else {
				b = b%a;
				b = b<0 ? -b : b;
			}
		}
		return (a+b)<<shift;
	}
	
	public static void main(String[] args) {
		GCD gcd = new GCD();

		//int a = 0x7fffffff;
		//int b = 0x7fffffff;
		int a = -(int)Math.pow(4, 10);
		int b = -(int)Math.pow(6, 10);

		System.out.println("GCD(" + a + ", " + b + ") = " + gcd.GCD(a, b));
	}
}
