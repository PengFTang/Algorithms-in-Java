
/**
 * An implementation of the KMP searching algorithm based on the understanding of this lecture: https://www.youtube.com/watch?v=GTJr8OvyEVQ
 * 
 * @author FLAG
 */

public class KMP {

	public int search(String str, String pattern) {
		char[] strs = str.toCharArray();
		char[] patterns = pattern.toCharArray();
		int L=strs.length, N=patterns.length, i=0, j=0; // i: str pointer, j: pattern pointer
		if(N<1) return 0;
		if(L<N) return -1;
		int[] lps = lps(pattern); // get the array that stores the longest subarray whose prefix is also its suffix
		while(i<L) {
			if(strs[i]==patterns[j]) { // same value found, move both str and pattern pointers to their right
			    ++i; 
			    ++j;
			    if(j==N) return i-N; // whole match found
			}
			else if(j>0) j = lps[j-1]; // move pattern pointer to a previous safe location
			else ++i; // restart searching at next str pointer
		}
		return -1;
	}
	
	private int[] lps(String pattern) {
		int j=0, i=1, L=pattern.length();
		int[] res = new int[L];
		char[] chars = pattern.toCharArray();
		while(i<L) {
			if(chars[i]==chars[j]) res[i++] = ++j;
			else {
				int temp = i-1;
				while(temp>0) {
					int prevLPS = res[temp];
					if(chars[i]==chars[prevLPS]) {
						res[i++] = prevLPS+1;
						j = prevLPS;
						break;
					}
					else temp = prevLPS-1;
				}
				if(temp<=0) {
					res[i++] = 0;
					j = 0;
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		KMP kmp = new KMP();

		//String str = "babba";
		//String pattern = "bbb";
		String str = "abxabcabcaby";
		String pattern = "abcaby";
		
		System.out.println(kmp.search(str, pattern));
	}
}
