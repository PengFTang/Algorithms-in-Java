/**
 * The change-making problem addresses the following question: how can a given amount of money be made with the least number of coins of given denominations?
 * 
 * The class gives two implementations: an iterative one and a recursive one.
 * 
 * @author Peng F. Tang
 * pengftang@gmail.com
 */

public class CoinChange {
	
	/**
	 * an iterative implementation
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int coinChangeIterative(int[] coins, int amount) {
	    if(amount<1) return 0;
	    int[] dp = new int[amount+1];
	    int sum = 0;

	    while(++sum<=amount) {
	        int min = -1;
	        for(int coin : coins) {
	            if(sum >= coin && dp[sum-coin]!=-1) {
	                int temp = dp[sum-coin]+1;
	                min = min<0 ? temp : (temp < min ? temp : min);
	            }
	        }
	        dp[sum] = min;
	    }
	    return dp[amount];
	}
	
	/**
	 * a recursive implementation 
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int coinChangeRecursive(int[] coins, int amount) {
	    if(amount<1) return 0;
	    return helper(coins, amount, new int[amount]);
	}
	private int helper(int[] coins, int rem, int[] count) { // rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
	    if(rem<0) return -1; // not valid
	    if(rem==0) return 0; // completed
	    if(count[rem-1] != 0) return count[rem-1]; // already computed, so reuse
	    int min = Integer.MAX_VALUE;
	    for(int coin : coins) {
	        int res = helper(coins, rem-coin, count);
	        if(res>=0 && res < min)
	            min = 1+res;
	    }
	    count[rem-1] = (min==Integer.MAX_VALUE) ? -1 : min;
	    return count[rem-1];
	}	
	
	public static void main(String[] args) {
		CoinChange cc = new CoinChange();

		int[] coins = {1,2,5};
		int amount = 3459;
		
		
		long start1 = System.nanoTime();
		int res1 = cc.coinChangeIterative(coins, amount);
		long end1 = System.nanoTime();
		double duration1 = (end1-start1)/1000000.;
		
		long start2 = System.nanoTime();
		int res2 = cc.coinChangeIterative(coins, amount);
		long end2 = System.nanoTime();
		double duration2 = (end2-start2)/1000000.;
		
		System.out.println("result: " + res1 + "; " + res2);
		System.out.println("duration (iterative): " + duration1 + "ms");
		System.out.println("duration (recursive): " + duration2 + "ms");
	}

}
