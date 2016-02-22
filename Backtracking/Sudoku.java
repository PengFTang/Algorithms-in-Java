package backtracking;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

public class Sudoku {
	private static final int N = 9; // board size
	private static final int Nsqrt = (int)Math.sqrt(N); // block size
	int[][] board = new int[N][N]; // Sudoku board
	/*
	 * level: 
	 * 1 (EASY) - mostly filled board
	 * 2 (MEDIUM) - halfly filled board
	 *  (HARD) - mostly empty board
	 */
	private static final double EMPTY_RATIO_EASY = 0.3, 
			EMPTY_RATIO_MEDIUM = 0.5, 
			EMPTY_RATIO_HARD = 0.7;
	
	/**
	 * construct a partially filled board randomly. Difficulty level is set to default, i.e., 1
	 */
	public Sudoku() {
		int level = 1;
		createRandomSudoku(level);
	}
	
	/**
	 * construct a partially filled board randomly
	 * @param level - difficulty level
	 */
	public Sudoku(int level) {
		createRandomSudoku(level);
	}
	
	/**
	 * construct a partially filled board with input (2D int matrix)
	 * @param board - specified input
	 */
	public Sudoku(int[][] board) {
		this.board = board;
		System.out.println("Original board:");
		printSudoku();
		assert(isValid());
	}
	
	/**
	 * construct a partially filled board with input (string array)
	 * @param board - specified input
	 */
	public Sudoku(String[] board) {
		for(int i=0; i<N; i++) {
			String[] str = board[i].split(",");
	    	for(int j=0; j<N; j++) {
	    		this.board[i][j] = Integer.parseInt(str[j]);
	    	}
		}
		System.out.println("Original board:");
		printSudoku();
		assert(isValid());
	}
	
	/**
	 * create a partially filled Sudoku board
	 * @param level - difficulty level
	 */
	public void createRandomSudoku(int level) {
		int totalLocations = N*N, remove = 0;
		
		//push all N*N empty spots into the stack
		Deque<Integer> emptyLocationList = new ArrayDeque<>();
		for(int i=totalLocations-1; i>=0; i--) {
			emptyLocationList.push(i);
		}
		
		Random rm = new Random();
		
		// solve the empty Sudoku board
		solve(emptyLocationList, true, rm); // solve the sudoku
		
		// set number of spots to be removed
		switch(level) {
			case 2:
				remove = (int)(totalLocations * EMPTY_RATIO_MEDIUM);
				break;
			case 3:
				remove = (int)(totalLocations * EMPTY_RATIO_HARD);
				break;
			case 1:
			default:
				remove = (int)(totalLocations * EMPTY_RATIO_EASY);
		}
		
		// randomly shuffle all indices from 0 to totalLocations-1
		int[] allLocations = new int[totalLocations];
		for(int i=0; i<totalLocations; i++) {
			allLocations[i] = i;
		}
		shuffle(allLocations);
		
		// remove specified amount of spots
		int startIndex = rm.nextInt(totalLocations);
		while(remove-- > 0) {
			int row = allLocations[startIndex]  / N;
			int col = allLocations[startIndex] % N;
			board[row][col] = 0;
			startIndex = (startIndex+1)%totalLocations;
		}
		
		// print out randomly initialized board
		System.out.println("Original board:");
		printSudoku();
	}
	
	/**
	 * shuffle an int array
	 * @param nums - int array
	 */
	private void shuffle(int[] nums) {
		Random rm = new Random();
		for(int i=1, L=nums.length; i<L; i++) {
			int pos = rm.nextInt(i+1);
			swap(nums, pos, i);
		}
	}
	
	/**
	 * swap two elements in an int array
	 * @param nums - int array
	 * @param i - index of first element
	 * @param j - index of second element
	 */
	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	/**
	 * solve Sudoku main function
	 */
	public void solveSudoku() {
		Deque<Integer> emptyLocationList = getEmptySpots(0, 0);
        if(!solve(emptyLocationList, false, new Random())) {
        	System.out.println("No solution!");
        }
        else {
        	System.out.println("Solution: ");
        	printSudoku();
        }
    } 
	
	/**
	 * solve the Sudoku utility function. return false except when all empty spots are filled
	 * @param emptyLocationList - list of empty spots
	 * @param startWithRandomInitialValue - flag of whether starting with a random initial value
	 * @param rm - Random instance
	 * @return true if all empty spots are filled
	 */
	private boolean solve(Deque<Integer> emptyLocationList, boolean startWithRandomInitialValue, Random rm) { 
		if(emptyLocationList.size()==0) return true;
		/*{
			printSudoku();
			return false;
		}*/
		int firstValue = emptyLocationList.peek();
		int row = firstValue/N, col = firstValue%N;
		int startValue = startWithRandomInitialValue ? rm.nextInt(N) : 0;
		for(int k=startValue; k<=N+startValue; k++) {
        	int trueValue = k%N + 1;
        	if(isSafe(board, row, col, trueValue)) {
        		board[row][col] = trueValue;
            	emptyLocationList.pop();
        		if(solve(emptyLocationList, startWithRandomInitialValue, rm)) return true;
        		board[row][col] = 0;
        		emptyLocationList.push(firstValue);
        	}
        }
        return false;
	}
	
	/**
	 * check if placing 'ch' at location (i, j) is safe or not
	 * @param board - partially filled Sudoku board
	 * @param i - row index
	 * @param j - column index
	 * @param ch - number to be placed at location (i, j)
	 * @return true if placing 'ch' at location (i, j) is allowed, otherwise return false
	 */
    private boolean isSafe(int[][] board, int i, int j, int ch) {
		for(int k=0; k<N; k++) {
    		if(board[k][j]==ch) return false;
    		if(board[i][k]==ch) return false;
    	}
    	int starti = Nsqrt * (i/Nsqrt), startj = Nsqrt * (j/Nsqrt);
    	for(int k=starti; k<starti+Nsqrt; k++) {
    		for(int l=startj; l<startj+Nsqrt; l++) {
    			if(board[k][l]==ch) return false;
    		}
    	}
        return true;
    }
	
    /**
     * add all empty spots of the board into a stack
     * @param starti - start row index
     * @param startj - start column index
     * @return stack of all empty spots
     */
	private Deque<Integer> getEmptySpots(int starti, int startj) {
		Deque<Integer> emptyLocationList = new ArrayDeque<>();
		for(int i=starti; i<N+starti; i++) {
	    	for(int j=startj; j<N+startj; j++) {
	    		int row = i%N, col = j%N;
	    		if(board[row][col]==0) {
	    			emptyLocationList.push(N*row+col);
	    		}
	    	}
		}
		return emptyLocationList;
	}
	
	/**
	 * check if a Sudoku board is valid
	 * @return true if the board is valid; false if not
	 */
    public boolean isValid() {
    	for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
            	if(!isValidBlock(0, j, N, j) || !isValidBlock(i, 0, i, N)) return false;
                if(i%Nsqrt==0 && j%Nsqrt==0 && !isValidBlock(i, j, i+Nsqrt, j+Nsqrt)) return false;
            }
        }
        return true;
    }
    /**
     * check if a given block defined by start and end row and column index is valid
     * @param starti - start row index
     * @param startj - start column index
     * @param endi - end row index
     * @param endj - end column index
     * @return true if the block is valid
     */
    private boolean isValidBlock(int starti, int startj, int endi, int endj) {
    	int[] digits = new int[N];
        for(int i=starti; i<endi; i++) {
            for(int j=startj; j<endj; j++) {
            	if(board[i][j]>0 && board[i][j]<=N && ++digits[board[i][j]-1]>1) return false;
            }
        }
        return true;
    }
    
	/**
	 * print Sudoku board
	 */
    private void printSudoku() {
		System.out.print("╔");
		int col = 0;
		while(++col<N) {
			System.out.print("═══╦");
		}
		System.out.println("═══╗");
		for(int i=0; i<N; i++) {
			if(i%Nsqrt==0 && i>0) {
				System.out.print("╠");
				col = 0;
				while(++col<N) {
					System.out.print("═══╬");
				}
				System.out.println("═══╣");
			}
			else if(i>0) {
				System.out.print("╠");
				col = 0;
				while(++col<N) {
					if(col%Nsqrt==0) System.out.print("───╬");
					else System.out.print("───┼");
				}
				System.out.println("───╣");
			}
			
			for(int j=0; j<N; j++) {
				if(j%Nsqrt==0) System.out.print("║");
				else System.out.print("│");
				String show = "";
				if(board[i][j]==0) show = "   ";
				else if(board[i][j]<10) show = " " + board[i][j] + " ";
				else show = board[i][j] + " ";
				System.out.print(show);
			}
			System.out.println("║");
		}

		System.out.print("╚");
		col = 0;
		while(++col<N) {
			System.out.print("═══╩");
		}
		System.out.println("═══╝");
		System.out.println();
    }

	public static void main(String[] args) {
		
		/*
		// get the solution of a given board:
		String[] board = {
				"0,0,9,7,4,8,0,0,0",
				"7,0,0,0,0,0,0,0,0",
				"0,2,0,1,0,9,0,0,0",
				"0,0,7,0,0,0,2,4,0",
				"0,6,4,0,1,0,5,9,0",
				"0,9,8,0,0,0,0,0,0",
				"0,0,0,8,0,0,0,2,0",
				"0,0,0,0,0,0,0,0,6",
				"0,0,0,2,7,5,9,0,0",
		};
		Sudoku q = new Sudoku(board);
		q.solveSudoku();
		*/
		
		/*
		// get the solution of a given board:
		Scanner sc = new Scanner(System.in);
		int[][] board = new int[N][N];
		System.out.println("Type the values line by line and seperated by a space:\n"
				+ "Example input:\n"
				+ "1 2 3 4 5 6 7 8 9\n"
				+ "0 0 0 0 0 0 0 0 0\n"
				+ "0 0 0 0 0 0 0 0 0\n"
				+ "0 0 0 0 0 0 0 0 0\n"
				+ "0 0 0 0 0 0 0 0 0\n"
				+ "0 0 0 0 0 0 0 0 0\n"
				+ "0 0 0 0 0 0 0 0 0\n"
				+ "0 0 0 0 0 0 0 0 0\n"
				+ "0 0 0 0 0 0 0 0 0\n");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				try {
					int next = sc.nextInt();
					assert(next>=0 && next<=N);
					board[i][j] = next;
				}
				catch(InputMismatchException e) { }
			}
		}
		sc.close();
		Sudoku q = new Sudoku(board);
		q.solveSudoku();
		*/
		
		// generate a random board and get the solution:
		Scanner sc = new Scanner(System.in);
		System.out.print("1 - easy\n2 - medium\n3 - hard\nSelect level: ");
		int level = sc.nextInt();
		Sudoku q = new Sudoku(level);

		System.out.print("Type anything and enter to get the solution: ");
		if(sc.hasNext())
			q.solveSudoku();
		sc.close();
	}    
}
