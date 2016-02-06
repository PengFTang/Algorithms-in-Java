public class ContainerWithMostWater {
	
    public int maxArea(int[] height) {
        int L=height.length, lo = 0, hi=L-1;
        if(L<2) return 0;
        int max = 0;
        while(lo<hi) {	  
	        int loMax = height[lo], hiMax = height[hi];      
	
        	int candidate = (hi-lo) * (loMax<hiMax ? loMax : hiMax);
        	max = candidate > max ? candidate : max;

        	if(height[lo]<=height[hi]) 
        	    while(lo<L-1 && height[lo]<=loMax) ++lo; 
        	else 
        	    while(hi>0 && height[hi]<=hiMax) --hi;
        }
        return max;
    }
	
	public static void main(String[] args) {
		ContainerWithMostWater q = new ContainerWithMostWater();

		int[] height = {5,2,12,1,5,3,4,11,9,4};
		
		int res = q.maxArea(height);

		System.out.println(res);
	}
}
