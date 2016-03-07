// package supports;

public class Methods {
	
	public static void printArray(int[] arr) {
		System.out.print("array: ");
		for(int i : arr)
			System.out.print(i + " ");
		System.out.println();
	}

	public static void printArray(char[] arr) {
		System.out.print("array:\t");
		for(char i : arr)
			System.out.print(i + "");
		System.out.println();
	}
	public static void printArray(String[] arr) {
		System.out.print("array: ");
		for(String i : arr)
			System.out.print(i + " ");
		System.out.println();
	}
	public static void printArray(boolean[] arr) {
		System.out.print("array: ");
		for(boolean i : arr)
			System.out.print((i?1:0) + " ");
		System.out.println();
	}

	public static void printMatrix(char[][] matrix) {
		int N = matrix[0].length;
		System.out.print("matrix:\n");
		for(char[] row : matrix) {
			for(int j=0; j<N; j++)
				System.out.print(row[j] + " ");
			System.out.println();
		}
	}
	public static void printMatrix(int[][] matrix) {
		int N = matrix[0].length;
		System.out.print("matrix:\n");
		for(int[] row : matrix) {
			for(int j=0; j<N; j++)
				System.out.print(row[j] + " ");
			System.out.println();
		}
	}
	public static void printMatrix(long[][] matrix) {
		int N = matrix[0].length;
		System.out.print("matrix:\n");
		for(long[] row : matrix) {
			for(int j=0; j<N; j++)
				System.out.print(row[j] + " ");
			System.out.println();
		}
	}
	public boolean  isSymmetric(int[][] matrix) {
		int rows = matrix.length;
		if(rows<1) return true;
		int cols = matrix[0].length;
		if(rows!=cols) return false;
		for(int i=0; i<rows; i++) {
			for(int j=i+1; j<cols; j++) {
				if(matrix[i][j]!=matrix[j][i]) return false;
			}
		}
		return true;
	}
}
