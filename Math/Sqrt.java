package Math;

/**
 * implements (int)Math.sqrt(x) with two different methods
 * 
 * @author FLAG
 */

public class Sqrt {

	/**
	 * implements sqrt using a basic binary search strategy
	 */
	public int sqrt(int x) {
		if(x<0) throw new ArithmeticException("positive number needed");
        if(x<=1) return x;
        int lo=1, hi=x;
        while (lo+1<hi) {
            int mid = lo + ((hi-lo)>>1);
            if(mid > x/mid) hi=mid;
            else lo=mid;
        }
        return lo;
    }
	
	/**
	 * implements sqrt using bit-wise operation
	 */
	public int sqrt2(int x) {
		if(x<0) throw new ArithmeticException("positive number needed");
		int res = 0;
		for(int bits=1<<15; bits!=0; bits=bits>>1) {
			int next = res | bits;
			if(next*next <= x) res = next;
		}
		return res;
	}

	public static void main(String[] args) {
		Sqrt sqrt = new Sqrt();
		
		//int x = -1;
		//int x = 10;
		//int x = 121;
		int x = 0x7fffffff;

		System.out.println(sqrt.sqrt(x));
		System.out.println("built-in: " + (int)Math.sqrt(x));
	}
}
