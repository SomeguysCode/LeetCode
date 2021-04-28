import java.util.Arrays;

public class SudokuSolver {
    public static void solveSudoku(char[][] board) {
        boolean singleFound = loopCheck(board);
        while (singleFound) {
            singleFound = loopCheck(board);
        }
        recursiveCheck(board, 0, 0);
    }

    public static boolean recursiveCheck(char[][] board, int row, int col) {
        if (col > 8) {
            row++;
            col = 0;
            if (row > 8) {
                return true;
            }
        }
        while (board[row][col] != '.') {
            col++;
            if (col > 8) {
                row++;
                col = 0;
                if (row > 8) {
                    return true;
                }
            }
        }

        boolean[] possibleValues = validValueGen(row, col, board);
        for (int i = 0; i < 9; i++) {
            if (possibleValues[i]) {
                continue;
            }
            board[row][col] = (char) (i + 49);
            if (recursiveCheck(board, row, col + 1)) {
                return true;
            }
            board[row][col] = '.';
        }

        return false;
    }

    public static boolean loopCheck(char[][] board) {
        int row = 0;
        int col = 0;
        boolean singleFound = false;
        while (true) {
            if (col > 8) {
                row++;
                col = 0;
                if (row > 8) {
                    return singleFound;
                }
            }
            if (board[row][col] == '.') {
                char single = singleValueGen(row, col, board);
                if (single != '0') {
                    board[row][col] = single;
                    singleFound = true;
                }
            }
            col++;
        }
    }

    public static boolean[] validValueGen(int row, int col, char[][] board) {
        boolean[] numPool = new boolean[9];
        for (int i = 0; i < 9; i++) {
            char boardVal = board[row][i];
            if (boardVal != '.') {
                numPool[boardVal - 49] = true;
            }
            boardVal = board[i][col];
            if (boardVal != '.') {
                numPool[boardVal - 49] = true;
            }
            int boxRow = 3 * (row / 3);
            int boxCol = 3 * (col / 3);
            boardVal = board[boxRow + i / 3][boxCol + i % 3];
            if (boardVal != '.') {
                numPool[boardVal - 49] = true;
            }
        }
        return numPool;
    }

    public static char singleValueGen(int row, int col, char[][] board) {
        boolean[] numPool = validValueGen(row, col, board);
        boolean oneFound = false;
        int index = 0;
        for (int i = 0; i < 9; i++) {
            if (!numPool[i]) {
                if (oneFound) {
                    return '0';
                }
                oneFound = true;
                index = i;
            }
        }
        return oneFound ? (char) (index + 49) : '0';
    }
}
