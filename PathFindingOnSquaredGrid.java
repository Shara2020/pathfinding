import java.util.Scanner;


/*************************************************************************
 * Student Name:Kamsharine Thayananthan 
 * Last update: 03-04-2017
 * Student ID:2015501
 * IIT ID:15833600
 *************************************************************************/
/*************************************************************************
 * Author: Dr E Kapetanios 
 * Last update: 22-02-2017
 *
 *************************************************************************/



public class PathFindingOnSquaredGrid {

	// given an N-by-N matrix of open cells, return an N-by-N matrix
	// of cells reachable from the top
	public static boolean[][] flow(boolean[][] open) {
		int N = open.length;

		boolean[][] full = new boolean[N][N];
		for (int j = 0; j < N; j++) {
			flow(open, full, 0, j);
		}

		return full;
	}

	// determine set of open/blocked cells using depth first search
	public static void flow(boolean[][] open, boolean[][] full, int i, int j) {
		int N = open.length;

		// base cases
		if (i < 0 || i >= N)
			return; // invalid row
		if (j < 0 || j >= N)
			return; // invalid column
		if (!open[i][j])
			return; // not an open cell
		if (full[i][j])
			return; // already marked as open

		full[i][j] = true;

		flow(open, full, i + 1, j); // down
		flow(open, full, i, j + 1); // right
		flow(open, full, i, j - 1); // left
		flow(open, full, i - 1, j); // up
	}

	// does the system percolate?
	public static boolean percolates(boolean[][] open) {
		int N = open.length;

		boolean[][] full = flow(open);
		for (int j = 0; j < N; j++) {
			if (full[N - 1][j])
				return true;
		}

		return false;
	}

	// does the system percolate vertically in a direct way?
	public static boolean percolatesDirect(boolean[][] open) {
		int N = open.length;

		boolean[][] full = flow(open);
		int directPerc = 0;
		for (int j = 0; j < N; j++) {
			if (full[N - 1][j]) {
				// StdOut.println("Hello");
				directPerc = 1;
				int rowabove = N - 2;
				for (int i = rowabove; i >= 0; i--) {
					if (full[i][j]) {
						// StdOut.println("i: " + i + " j: " + j + " " +
						// full[i][j]);
						directPerc++;
					} else
						break;
				}
			}
		}

		// StdOut.println("Direct Percolation is: " + directPerc);
		if (directPerc == N)
			return true;
		else
			return false;
	}

	// draw the N-by-N boolean matrix to standard draw
	public static void show(boolean[][] a, boolean which) {
		int N = a.length;
		StdDraw.setXscale(-1, N);
		
		StdDraw.setYscale(-1, N);
		StdDraw.setPenColor(StdDraw.BLUE);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (a[i][j] == which)
					StdDraw.square(j, N - i - 1, .5);
				else
					StdDraw.filledSquare(j, N - i - 1, .5);
	}

	// draw the N-by-N boolean matrix to standard draw, including the points A
	// (x1, y1) and B (x2,y2) to be marked by a circle
	public static void show(boolean[][] a, boolean which, int x1, int y1, int x2, int y2) {
		int N = a.length;
		StdDraw.setXscale(-1, N);
                StdDraw.setYscale(-1, N);
		StdDraw.setPenColor(StdDraw.BLUE);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (a[i][j] == which)
					if ((i == x1 && j == y1) || (i == x2 && j == y2)) {
						StdDraw.circle(j, N - i - 1, .5);
					} else
						StdDraw.square(j, N - i - 1, .5);
				else
					StdDraw.filledSquare(j, N - i - 1, .5);
	}

	// return a random N-by-N boolean matrix, where each entry is
	// true with probability p
	public static boolean[][] random(int N, double p) {
		boolean[][] a = new boolean[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				a[i][j] = StdRandom.bernoulli(p);
		return a;
	}

	// test client
	public static void main(String[] args) {
		// boolean[][] open = StdArrayIO.readBoolean2D();

		// The following will generate a 10x10 squared grid with relatively few
		// obstacles in it
		// The lower the second parameter, the more obstacles (black cells) are
		// generated
                
                Scanner in = new Scanner(System.in);
                System.out.println("input value for N, it should be an integer and greater than 0");
                int v=in.nextInt();
                
                System.out.println("input value for Blocked cells should be less than one and should be a decimal number ");
                float c=in.nextFloat();
		boolean[][] randomlyGenMatrix = random(v, c);//N,Blocked Cell

		StdArrayIO.print(randomlyGenMatrix);
		show(randomlyGenMatrix, true);

		System.out.println();
		System.out.println("The system percolates: " + percolates(randomlyGenMatrix));

		System.out.println();
		System.out.println("The system percolates directly: " + percolatesDirect(randomlyGenMatrix));
		System.out.println();

		// Reading the coordinates for points A and B on the input squared grid.

		// THIS IS AN EXAMPLE ONLY ON HOW TO USE THE JAVA INTERNAL WATCH
		// Start the clock ticking in order to capture the time being spent on
		// inputting the coordinates
		// You should position this command accordingly in order to perform the
		// algorithmic analysis
		Stopwatch timerFlow = new Stopwatch();

		//Scanner in = new Scanner(System.in);
		System.out.println("Enter x point for A > ");
		int Ax = in.nextInt();

		System.out.println("Enter y point for A > ");
		int Ay = in.nextInt();

		System.out.println("Enter x point for B > ");
		int Bx = in.nextInt();

		System.out.println("Enter y point for B > ");
		int By = in.nextInt();

                for(int j=0; j<3; j++){//TO RUN THE PROGRAM 3 TIMES
		StdOut.println("Enter Distance Method >,0 for Manhatance ,1 for Euclidean and 2 for Chebyshe ");
		int disMethod = in.nextInt();
                StdOut.println("Your choice is  "+disMethod);

		// THIS IS AN EXAMPLE ONLY ON HOW TO USE THE JAVA INTERNAL WATCH
		// Stop the clock ticking in order to capture the time being spent on
		// inputting the coordinates
		// You should position this command accordingly in order to perform the
		// algorithmic analysis
		StdOut.println("Elapsed time = " + timerFlow.elapsedTime());
                //StdOut.println("Total Cost = " + AStar.t_final_Cost);
               

		// System.out.println("Coordinates for A: [" + Ax + "," + Ay + "]");
		// System.out.println("Coordinates for B: [" + Bx + "," + By + "]");

		show(randomlyGenMatrix, true, Ax, Ay, Bx, By);

		AStar_Path_Finding.Case[][] grid = AStar_Path_Finding.AStar_Path_Finding(randomlyGenMatrix, Ax, Ay, Bx, By, disMethod);

		AStar_Path_Finding.Case endCase = grid[Bx][By];

		if (endCase.parent != null) {
			
			AStar_Path_Finding.Case p = endCase.parent;
			while (p != null) {
				StdDraw.filledSquare(p.j, grid.length - p.i - 1, .5);
				p = p.parent;
                                
			}
		} else {
			System.out.println("There is no path");
		}
                }
	}
        
}
