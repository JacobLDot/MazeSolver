/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     *
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        ArrayList<MazeCell> path = new ArrayList<>();
        MazeCell current = maze.getEndCell();

        while (current != null) {
            path.add(0, current);
            current = current.getParent();
        }

        return path;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     *
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> stack = new Stack<>();
        MazeCell start = maze.getStartCell();
        MazeCell end = maze.getEndCell();

        stack.push(start);
        start.setExplored(true);

        while (!stack.isEmpty()) {
            MazeCell current = stack.pop();

            if (current == end) {
                return getSolution();
            }

            int row = current.getRow();
            int col = current.getCol();

            MazeCell[] neighbors = new MazeCell[4];
            if (maze.isValidCell(row - 1, col)) {
                neighbors[0] = maze.getCell(row - 1, col);
            }
            if (maze.isValidCell(row, col + 1)) {
                neighbors[1] = maze.getCell(row, col + 1);
            }
            if (maze.isValidCell(row + 1, col)) {
                neighbors[2] = maze.getCell(row + 1, col);
            }
            if (maze.isValidCell(row, col - 1)) {
                neighbors[3] = maze.getCell(row, col - 1);
            }

            for (MazeCell neighbor : neighbors) {
                if (neighbor != null && maze.isValidCell(neighbor.getRow(), neighbor.getCol())) {
                    neighbor.setExplored(true);
                    neighbor.setParent(current);
                    stack.push(neighbor);
                }
            }
        }


        return new ArrayList<>();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> queue = new LinkedList<>();
        MazeCell start = maze.getStartCell();
        MazeCell end = maze.getEndCell();

        queue.add(start);
        start.setExplored(true);

        while (!queue.isEmpty()) {
            MazeCell current = queue.poll();

            if (current == end) {
                return getSolution();
            }

            int row = current.getRow();
            int col = current.getCol();
            MazeCell[] neighbors = new MazeCell[4];
            if (maze.isValidCell(row - 1, col)) {
                neighbors[0] = maze.getCell(row - 1, col);
            }
            if (maze.isValidCell(row, col + 1)) {
                neighbors[1] = maze.getCell(row, col + 1);
            }
            if (maze.isValidCell(row + 1, col)) {
                neighbors[2] = maze.getCell(row + 1, col);
            }
            if (maze.isValidCell(row, col - 1)) {
                neighbors[3] = maze.getCell(row, col - 1);
            }

            for (MazeCell neighbor : neighbors) {
                if (neighbor != null && maze.isValidCell(neighbor.getRow(), neighbor.getCol())) {
                    neighbor.setExplored(true);
                    neighbor.setParent(current);
                    queue.add(neighbor);
                }
            }
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
