public class RotateArray {
    public void rotate(int[] nums, int k) {
    	int L=nums.length, counter=0, currentLoc=0, starti=0, prevVal=nums[currentLoc];
    	k = k%L;
    	while(counter++<L) {
    		if(currentLoc>=L && currentLoc%L==starti) { // this handles the case when currentLoc moves back to where started.
    		    currentLoc = ++starti; 
    		    prevVal=nums[currentLoc];
    		}
    		
    		int nextLoc = (currentLoc+k)%L; // get index of next location
    		int nextVal = nums[nextLoc]; // get value at next location
    		nums[nextLoc] = prevVal; // update value at next location
    		prevVal = nextVal; // update previous value

    		currentLoc += k; // move current to next location
    	}
    }	
    
    public static void main(String[] args) {
		RotateArray q = new RotateArray();

		int[] nums = {1,2,3,4,5,6,7,8,9};
		int k = 5;
		
		q.rotate(nums, k);
		for(int i : nums) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
