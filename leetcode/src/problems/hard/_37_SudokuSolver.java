package problems.hard;

import java.util.ArrayDeque;

public class _37_SudokuSolver {
    public void solveSudoku(char[][] board) {
        int n = board.length;

        ArrayDeque<Cell> emptyCells = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                if (c == '.') {
                    emptyCells.addLast(new Cell(i, j));
                }
            }
        }

        solve(board, emptyCells, null, 0);
    }

    public boolean solve(char[][] board, ArrayDeque<Cell> emptyCells, Cell lastCell, int depth) {
        if (lastCell != null) {
            for (int col = 0; col < 9; col++) {
                if (board[lastCell.i][col] == board[lastCell.i][lastCell.j] && col != lastCell.j) {
                    return false;
                }
            }

            for (int row = 0; row < 9; row++) {
                if (board[row][lastCell.j] == board[lastCell.i][lastCell.j] && row != lastCell.i) {
                    return false;
                }
            }

            int squareI = (lastCell.i/3) * 3;
            int squareJ = (lastCell.j/3) * 3;
            for (int col = 0; col < 3; col++) {
                for (int row = 0; row < 3; row ++) {
                    if (board[squareI + row][squareJ + col] == board[lastCell.i][lastCell.j] &&
                            (squareI + row != lastCell.i || squareJ + col != lastCell.j)) {
                        return false;
                    }
                }
            }
        }

        if (emptyCells.isEmpty()) {
            return true;
        }

        Cell currentCell = emptyCells.removeFirst();
        int i = currentCell.i;
        int j = currentCell.j;

        for (int num = 1; num <= 9; num++) {
            board[i][j] = (char)(num + '0');
            boolean valid = solve(board, emptyCells, currentCell, depth+1);
            if (valid) {
                return true;
            }

            board[i][j] = '.';
        }

        emptyCells.addFirst(currentCell);
        return false;
    }

    static class Cell {
        int i;
        int j;

        public Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
