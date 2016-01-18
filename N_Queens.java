/**
 * A backtracking method to solve N-Queen puzzle.
 * 
 * The N-Queen puzzle is the problem of placing chess queens on a square chess board so that no two queens threaten each other.
 * 
 * @author Peng F. Tang
 * pengftang@gmail.com
 */

public class N_Queens {
	private int count = 0;
	public static void main(String[] args) {
		N_Queens nq = new N_Queens();
		
		int N = 4; // board size
		if(N<1) return;
		char[][] board = new char[N][N];
		for(char[] row : board) {
			for(int j=0; j<N; j++) {
				row[j] = '.';
			}
		}
		nq.solve(board, N, 0);
		System.out.println(nq.count + " solution(s)");
	}
	
	/**
	 * checks if placing Q at [row][col] is safe
	 * 
	 * @param board 2D board char array
	 * @param N size of board
	 * @param row current row working on
	 * @param col current column working on
	 * @return true if it's safe to place Q at [row][col]; false if otherwise
	 */
	private boolean isSafe(char[][] board, int N, int row, int col) {
		for(int i=0; i<N; i++) {
			if(board[i][col]!='.') return false;
			if(board[row][i]!='.') return false;
		}
		int step = 1;
		while(row-step>=0 && col-step>=0)
			if(board[row-step][col-step++]!='.') return false;
		step = 1;
		while(row+step<N && col-step>=0)
			if(board[row+step][col-step++]!='.') return false;
		return true;
	}
	
	/**
	 * solves the N-Queen problem by moving forward column by column if safe and moving backward if not
	 * 
	 * @param board 2D board char array
	 * @param N size of board
	 * @param col current column working on
	 * @return true if find a location to place Q at col; false if not or done with placing all N Q's
	 */
	private boolean solve(char[][] board, int N, int col) {
		if(col==N) { // done soving, simply add the board into the result
			System.out.println("Solution " + (++count) + ":");
			for(char[] row : board) {
				for(int j=0; j<N; j++)
					System.out.print(row[j] + " ");
				System.out.println();
			}
			System.out.println();
			return false; // return false so will backtrack
		}
		for(int i=0; i<N; i++) {
			if(isSafe(board, N, i, col)) {
				board[i][col] = '#'; // greedy
				if(solve(board, N, col+1)) return true;
				else board[i][col] = '.'; // backtrack
			}
		}
		return false;
	}
}
