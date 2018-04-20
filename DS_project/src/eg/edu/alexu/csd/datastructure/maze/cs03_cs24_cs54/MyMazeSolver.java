package eg.edu.alexu.csd.datastructure.maze.cs03_cs24_cs54;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import eg.edu.alexu.csd.datastructure.maze.IMazeSolver;
import eg.edu.alexu.csd.datastructure.queue.cs03_cs24_cs54.MyQueuelinked;
import eg.edu.alexu.csd.datastructure.stack.cs54.stack;

public class MyMazeSolver implements IMazeSolver {
	private String[] mazeMap;
	private int rows;
	private int cols;
	private boolean[][] visited;
	private HashMap<Point, Point> path;
	final int POSITIONS = 4;
	
	
	@Override
	public int[][] solveBFS(File maze) {
		path = new HashMap<Point, Point>();
		readMaze(maze);
		Point endPoint = null;
		MyQueuelinked queue = new MyQueuelinked();
		
		Point start = findStart();
		queue.enqueue(start);
		
		while (!queue.isEmpty()) {
			Point currentPoint = (Point) queue.dequeue();
			markVisited(currentPoint);
			
			if (cellChar(currentPoint) == 'E') {
				endPoint = currentPoint;
				break;
			}
			
			Point [] children = new Point[POSITIONS];
			children[0] = new Point(currentPoint.x, 
					currentPoint.y - 1);
			children[1] = new Point(currentPoint.x - 1, 
					currentPoint.y);
			children[2] = new Point(currentPoint.x, 
					currentPoint.y + 1);
			children[3] = new Point(currentPoint.x + 1, 
					currentPoint.y);
			
			for (Point child : children) {
				if (isValidCell(child) && !isVisited(child) 
						&& cellChar(child) != '#') {
					queue.enqueue(child);
					path.put(child, currentPoint);
				}
			}
		}
		if (endPoint == null) {
			return null;
		}
		
		return findPath(endPoint);
	}

	@Override
	public int[][] solveDFS(File maze) {
		path = new HashMap<Point, Point>();
		readMaze(maze);
		Point endPoint = null;
		stack stack = new stack();
		
		Point start = findStart();
		stack.push(start);
		
		while (!stack.isEmpty()) {
			Point currentPoint = (Point) stack.pop();
			markVisited(currentPoint);
			
			if (cellChar(currentPoint) == 'E') {
				endPoint = currentPoint;
				break;
			}
			
			Point [] children = new Point[4];
			children[0] = new Point(currentPoint.x, 
					currentPoint.y - 1);
			children[1] = new Point(currentPoint.x - 1, 
					currentPoint.y);
			children[2] = new Point(currentPoint.x, 
					currentPoint.y + 1);
			children[3] = new Point(currentPoint.x + 1, 
					currentPoint.y);
			
			
			for (Point child : children) {
				if (isValidCell(child) && !isVisited(child) 
						&& cellChar(child) != '#') {
					stack.push(child);
					path.put(child, currentPoint);
				}
			}
		}
		if (endPoint == null) {
			return null;
		}
		
		return findPath(endPoint);
	}
	
	/**
	 * Reads the maze from a file.
	 * @param maze The file containing maze data
	 */
	private void readMaze(File maze) {
		BufferedReader bufferReader;
		try {
			bufferReader = new BufferedReader(new FileReader(maze.getPath()));
			String dimensions = bufferReader.readLine();
			String[] dimensionsStrings = dimensions.trim().split("\\s+");
			
			this.rows = Integer.parseInt(dimensionsStrings[0]);
			this.cols = Integer.parseInt(dimensionsStrings[1]);
			
			visited = new boolean[rows][cols];
			
			this.mazeMap = new String[rows];
			for (int i=0; i<rows; i++) {
				String row = new String(bufferReader.readLine());
				this.mazeMap[i] = row;
			}
			
			bufferReader.close();
		} catch (Exception e) {
			throw new RuntimeException("Error Reading.");
		}
		return;
	}
	
	/**
	 * @return returns a point object that holds the
	 * the start position.
	 */
	private Point findStart() {
		Point start = null;
		boolean startFound = false;
		boolean endFound = false;
		
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				if (mazeMap[i].charAt(j) == 'S') {
					start = new Point(i, j);
					startFound = true;
				}
				if (mazeMap[i].charAt(j) == 'E') {
					endFound = true;
				}
			}
		}
		
		if (!startFound || !endFound) {
			throw new RuntimeException("Invalid maze");
		}
		return start;
	}
	
	/**
	 * Returns an array of the path of the maze.
	 * @param endPoint Point object for the end cell of the maze
	 * @return 2D array of dimensions n*2
	 */
	private int[][] findPath(Point endPoint) {
		Point[] pointArray = new Point[rows*cols];
		Point currentPoint = endPoint;
		
		int counter = 0;
		while (currentPoint != null) {
			pointArray[counter] = currentPoint;
			currentPoint = path.get(currentPoint);
			counter++;
		}	
		int[][] intArray = new int[counter][2];
		for (int i = 0; i < counter; i++) {
			Point point = pointArray[counter- 1 - i];
			intArray[i][0] = point.x;
			intArray[i][1] = point.y;
		}
		return intArray;
	}
	
	/**
	 * Marks a cell visited in the visited array.
	 * @param p Point object for the cell to mark as visited.
	 */
	private void markVisited(Point p) {
		visited[p.x][p.y] = true;
	}
	
	/**
	 * Checks if a cell is already visited.
	 * @param p Point object for the cell to be checked.
	 * @return a boolean value of the state of the cell.
	 */
	private boolean isVisited(Point p) {
		return visited[p.x][p.y];
	}
	
	/**
	 * Checks if a given cell is valid in the maze.
	 * @param p Point to check.
	 * @return true if the cell is valid.
	 */
	private boolean isValidCell(Point p) {
		if (p.x >= 0 && p.x < rows && p.y >= 0 && p.y < cols) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the character in of a given cell in the maze.
	 * @param p Point object of the cell
	 * @return character representing this cell in the maze
	 */
	private char cellChar(Point p) {
		return mazeMap[p.x].charAt(p.y);
	}
}