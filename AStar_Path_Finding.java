
import java.util.*;

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
import java.util.*;

public class AStar_Path_Finding {
	public static final int DIAGONAL_COST = 14;
	public static final int V_H_COST = 10;

	static class Case {
		double heuristicCost = 0; // Heuristic cost-Distance From a node to the target node
		double finalMovementCost = 0; //Heuristic+Movnement cost
		int i, j;
		boolean blocked;//blocked cells
		Case parent;// node to reach end node 

		Case(int i, int j, boolean blocked) {
			this.i = i;
			this.j = j;
			this.blocked = blocked;
		}

		@Override
		public String toString() {
			return "[" + this.i + ", " + this.j + "]";
		}
	}

	// Blocked cells are just null Case values in grid
	static Case[][] grid = new Case[5][5];

	static PriorityQueue<Case> open = new PriorityQueue<>((Object o1, Object o2) -> {
		Case c1 = (Case) o1;
		Case c2 = (Case) o2;

		return c1.finalMovementCost < c2.finalMovementCost ? -1 : c1.finalMovementCost > c2.finalMovementCost ? 1 : 0;
	});;

	static List<Case> closed = new ArrayList<>();
	static int startI, startJ;
	static int endI, endJ;

	public static void setBlockedCase(int i, int j) {
		grid[i][j] = null;
	}

	public static void setStartCase(int i, int j) {
		startI = i;
		startJ = j;
	}

	public static void setEndCase(int i, int j) {
		endI = i;
		endJ = j;
	}

	static void checkAndUpdateCost(Case current, Case c, double cost) {
		if (c == null || closed.contains(c))
			return;
		double c_final_cost = c.heuristicCost + cost;
               
                

		boolean inOpen = open.contains(c);
		if (!inOpen || c_final_cost < c.finalMovementCost) {
			c.finalMovementCost = c_final_cost;
			c.parent = current;
			if (!inOpen)
				open.add(c);
                      
		}
                
	}

	public static Case[][] AStar_Path_Finding(boolean[][] gridA, int Ax, int Ay, int Bx, int By, int dMethod) {

		generateGrid(gridA, dMethod, Ax, Ay, Bx, By);

		// add the start location to open list.
		open.add(grid[Ax][Ay]);

		Case current;

		while (true) {
			current = open.poll();
			if (current == null)
				break;
			closed.add(current);

			if (current.equals(grid[Bx][By])) {
				break;
			}

			Case c;
			if (current.i - 1 >= 0) {
				c = grid[current.i - 1][current.j];
				if (!c.blocked)
					checkAndUpdateCost(current, c, current.finalMovementCost + V_H_COST);
			}

			if (current.j - 1 >= 0) {
				c = grid[current.i][current.j - 1];
				if (!c.blocked)
					checkAndUpdateCost(current, c, current.finalMovementCost + V_H_COST);
                                
			}

			if (current.j + 1 < grid[0].length) {
				c = grid[current.i][current.j + 1];
				if (!c.blocked)
					checkAndUpdateCost(current, c, current.finalMovementCost + V_H_COST);
			}

			if (current.i + 1 < grid.length) {
				c = grid[current.i + 1][current.j];
				if (!c.blocked)
					checkAndUpdateCost(current, c, current.finalMovementCost + V_H_COST);

			}
		}
		return grid;
	}
        
	private static void generateGrid(boolean[][] gridA, int dMethod, int Ai, int Aj, int Bi, int Bj) {
              for(int k=0;k<3;k++){
		grid = new Case[gridA.length][gridA.length];
		for (int i = 0; i < gridA.length; i++) {
			for (int j = 0; j < gridA.length; j++) {
				Case cell = new Case(i, j, !gridA[i][j]);
                                /**if (dMethod==0){
                                  cell.heuristicCost = Math.abs(i - Bi) + Math.abs(j + Bj);  
                                   System.out.println("Enter the 1 for Euclidean");
                                    if (dMethod==1){
                                        cell.heuristicCost = Math.pow(Math.pow(i - Bi, 2) + Math.pow(j - Bj, 2), .5);
                                        System.out.println("Enter the 1 for Euclidean");
                                    if (dMethod==2){
                                        cell.heuristicCost = Math.max(Math.abs(i - Bi), Math.abs(j - Bj));
                                    }
                                    }
                                    
                                }*/
                              
				switch (dMethod){
				case 0:
                                    //System.out.println("Enter the 1 for Euclidean");
                                    cell.heuristicCost = Math.abs(i - Bi) + Math.abs(j + Bj); 
                                    StdDraw.setPenColor(StdDraw.RED);
                                    break;
                                   
                                    //continue;
                                case 1:
                                //System.out.println("Enter the 2 for Euclidean");
					cell.heuristicCost = Math.pow(Math.pow(i - Bi, 2) + Math.pow(j - Bj, 2), .5);
					StdDraw.setPenColor(StdDraw.PINK);  
                                        //System.out.println("Select another method");
                                        
                                    break;
                                        //continue;
                                case 2:
					cell.heuristicCost = Math.max(Math.abs(i - Bi), Math.abs(j - Bj));
                                       StdDraw.setPenColor(StdDraw.MAGENTA);
                                        
					break;
				}
                                
				grid[i][j] = cell;
			}
                        }
		}

	}

	
}
/*
	 * Params : tCase = test case No. x, y = Board's dimensions si, sj = start
	 * location's x and y coordinates ei, ej = end location's x and y
	 * coordinates int[][] blocked = array containing inaccessible cell
	 * coordinates
	 */
	// public static void test(int tCase, int x, int y, int si, int sj, int ei,
	// int ej, int[][] blocked) {
	// System.out.println("\n\nTest Case #" + tCase);
	// // Reset
	// grid = new Case[x][y];
	// closed = new boolean[x][y];
	// open = new PriorityQueue<>((Object o1, Object o2) -> {
	// Case c1 = (Case) o1;
	// Case c2 = (Case) o2;
	//
	// return c1.finalMovementCost < c2.finalMovementCost ? -1 : c1.finalMovementCost > c2.finalMovementCost ? 1
	// : 0;
	// });
	// // Set start position
	// setStartCase(si, sj); // Setting to 0,0 by default. Will be useful for
	// // the UI part
	//
	// // Set End Location
	// setEndCase(ei, ej);
	//
	// for (int i = 0; i < x; ++i) {
	// for (int j = 0; j < y; ++j) {
	// grid[i][j] = new Case(i, j);
	// grid[i][j].heuristicCost = Math.abs(i - endI) + Math.abs(j - endJ);
	// // System.out.print(grid[i][j].heuristicCost+" ");
	// }
	// // System.out.println();
	// }
	// grid[si][sj].finalMovementCost = 0;
	//
	// /*
	// * Set blocked cells. Simply set the cell values to null for blocked
	// * cells.
	// */
	// for (int i = 0; i < blocked.length; ++i) {
	// setBlocked(blocked[i][0], blocked[i][1]);
	// }
	//
	// // Display initial map
	// System.out.println("Grid: ");
	// for (int i = 0; i < x; ++i) {
	// for (int j = 0; j < y; ++j) {
	// if (i == si && j == sj)
	// System.out.print("SO "); // Source
	// else if (i == ei && j == ej)
	// System.out.print("DE "); // Destination
	// else if (grid[i][j] != null)
	// System.out.printf("%-3d ", 0);
	// else
	// System.out.print("BL ");
	// }
	// System.out.println();
	// }
	// System.out.println();
	//
	// AStar();
	// System.out.println("\nScores for cells: ");
	// for (int i = 0; i < x; ++i) {
	// for (int j = 0; j < x; ++j) {
	// if (grid[i][j] != null)
	// System.out.printf("%-3d ", grid[i][j].finalMovementCost);
	// else
	// System.out.print("BL ");
	// }
	// System.out.println();
	// }
	// System.out.println();
	//
	// if (closed[endI][endJ]) {
	// // Trace back the path
	// System.out.println("Path: ");
	// Case current = grid[endI][endJ];
	// System.out.print(current);
	// while (current.parent != null) {
	// System.out.print(" -> " + current.parent);
	// current = current.parent;
	// }
	// System.out.println();
	// } else
	// System.out.println("No possible path");
	// }
