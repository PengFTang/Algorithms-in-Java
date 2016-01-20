package graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Computes the length of the longest increasing path in a 2D matrix of integers. Valid directions include: left, right, up or down.

 * @author Peng F. Tang
 * pengftang@gmail.com
 */


public class LongestIncreasingPath {

	public List<Integer> LIP(int[][] matrix) {
		List<Integer> list = new LinkedList<>();
	    int rows = matrix.length;
	    if(rows<1) return list;
	    int cols = matrix[0].length;
	    
	    // extend the original matrix to avoid considering speical boundary conditions
	    int[][] matrixExt = new int[rows+2][cols+2]; // extended matrix and result matrix
	    for(int i=1; i<rows+1; i++) {
	        matrixExt[i][0] = matrixExt[i][cols+1] = 0x7fffffff;
	        for(int j=1; j<cols+1; j++) {
	        	matrixExt[0][j] = matrixExt[rows+1][j] = 0x7fffffff;
	            matrixExt[i][j] = matrix[i-1][j-1];
	        }
	    }
	    
	    int[][] longest = new int[rows+2][cols+2]; 
	    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 4 different directions
	    int[] maxLoc = {0, 0}; // the location where LIP starts
	    int max = 1;
	    for(int i=1; i<rows+1; i++) {
	        for(int j=1; j<cols+1; j++) {
	            int temp = helper(matrixExt, longest, dirs, rows, cols, i, j);
	            max = temp>=max ? temp : max;
	            maxLoc = max==temp ? new int[]{i, j} : maxLoc;
	        }
	    }
	    
	    while(max-- > 0) {
	    	list.add(0, matrixExt[maxLoc[0]][maxLoc[1]]);
	    	for(int[] dir : dirs) {
	    		if(longest[maxLoc[0]+dir[0]][maxLoc[1]+dir[1]] == longest[maxLoc[0]][maxLoc[1]]-1) {
	    			maxLoc = new int[]{maxLoc[0]+dir[0], maxLoc[1]+dir[1]};
	    			break;
	    		}
	    	}
	    }
	    return list;
	}
	/**
	 * computes the longest increasing path starting from a given locatioin (i, j)
	 * 
	 * @param matrix extended matrix
	 * @param longest LIP value matrix
	 * @param dirs all valid moving directions
	 * @param rows number of rows
	 * @param cols number of columns
	 * @param i row index
	 * @param j column index
	 * @return LIP starting from (i, j) in matrix
	 */
	private int helper(int[][] matrix, int[][] longest, int[][] dirs, int rows, int cols, int i, int j) {
	    if(longest[i][j]>0) return longest[i][j]; // reuse
	    int max = 1; // result
	    for(int[] d : dirs) {
	        if(matrix[i][j] > matrix[i+d[0]][j+d[1]]) {
	            int temp = helper(matrix, longest, dirs, rows, cols, i+d[0], j+d[1]) + 1; // recursively update longest[i][j]
	            max = temp > max ? temp : max;
	        }
	    }
	    longest[i][j] = max; // store for reuse
	    return max;
	}
	

	
	public static void main(String[] args) {
		LongestIncreasingPath lip = new LongestIncreasingPath();

		/*int[][] matrix = {{3,2,1,4}};*/
		/*int[][] matrix = {
				{9,9,4},
				{6,6,8},
				{2,1,1}};*/
		/*int[][] matrix = {
				{3,4,5},
				{2,1,6},
				{9,8,7}};*/
		int[][] matrix = {
				{  10,    1,    2,    3,    4,    5,    6,    7,    8,    9},
				{  19,   18,   17,   16,   15,   14,   13,   12,   11,   10},
				{  20,   21,   22,   23,   24,   25,   26,   27,   28,   29},
				{  39,   38,   37,   36,   35,   34,   33,   32,   31,   30},
				{  40,   41,   42,   43,   44,   45,   46,   47,   48,   49},
				{  59,   58,   57,   56,   55,   54,   53,   52,   51,   50},
				{  60,   61,   62,   63,   64,   65,   66,   67,   68,   69},
				{  79,   78,   77,   76,   75,   74,   73,   72,   71,   70},
				{  80,   81,   82,   83,   84,   85,   86,   87,   88,   89},
				{  99,   98,   97,   96,   95,   94,   93,   92,   91,   90},
				{ 100,  101,  102,  103,  104,  105,  106,  107,  108,  109},  
				{ 119,  118,  117,  116,  115,  114,  113,  112,  111,  110},  
				{ 120,  121,  122,  123,  124,  125,  126,  127,  128,  129},  
				{ 139,  138,  137,  136,  135,  134,  133,  132,  131,  130},  
				{   0,  140,  141,  142,    0,    0,    0,    0,    0,    0}};
		
		long start = System.nanoTime();
		List<Integer> res = lip.LIP(matrix);
		long end = System.nanoTime();
		double duration = (end-start)/1000000.;
		
		System.out.println("LIP: " + res);
		System.out.println("time used: " + duration + "ms");
	}
}
