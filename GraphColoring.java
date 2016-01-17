import java.util.Random;

public class GraphColoring {
	
	// check if a vertex colored by `color` is allowed
	private boolean isSafe(int[][] G, int E, int[] colored, int v_id, int color) {
		for(int i=0; i<E; i++) {
			if(G[v_id][i]==1 && colored[i]==color) return false;
		}
		return true;
	}
	
	// main function that solves graph coloring
	private boolean draw(int[][] G, int E, int colors, int[] colored, int v_id) {
		if(v_id==E) return true;
		for(int color=1; color<=colors; color++) {
			if(isSafe(G, E, colored, v_id, color)) {
				colored[v_id] = color; // color current vertex
				if(draw(G, E, colors, colored, v_id+1)) return true;
				else {
					colored[v_id] = 0; // backtracking
					return false;
				}
			}
		}
		return false;
	}
	
	// prints out the solution
	public void solution(int[][] G) {
		int E = G.length;
		int[] colored = new int[E];
		int colors = E;
		if(draw(G, E, colors, colored, 0)) {
			System.out.println("Colored result:");
			for(int color : colored)
				System.out.print(color + " ");
			System.out.println("\n");
		}
		else {
			System.out.println("No solution!");
		}
	}
	
	public static void main(String[] args) {
		GraphColoring p = new GraphColoring();

		// random graph:
		int N = 10;
		Random rm = new Random();
		int[][] G = new int[N][N];
		for(int i=0; i<N; i++) {
			G[i][i] = 1;
			for(int j=i+1; j<N; j++) {
				G[i][j] = rm.nextDouble()<0.5 ? 0 : 1;
				G[j][i] = G[i][j];
			}
		}
		
		/*
		// fixed graph: 
		int N = 5;
		int[][] G = new int[N][N];
		
		G = new int[][]{
				{1,1,1,0,1},
				{1,1,0,1,1},
				{1,0,1,1,0},
				{0,1,1,1,1},
				{1,0,0,1,1}};
		*/
		
		// print out graph
		for(int[] row : G) {
			for(int j=0; j<N; j++)
				System.out.print(row[j] + " ");
			System.out.println();
		}
		System.out.println();

		p.solution(G);
	}
}
