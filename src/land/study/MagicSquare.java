package land.study;

import java.util.Arrays;

/**
 * Created by Thuc on 5/14/2016.
 * MAGICSQUARE1
 */
public class MagicSquare {
    private final int n;
    private int[][] matrix;
    private boolean[] used;
    private int solutionCount;
    private int magicSum;

    public MagicSquare(int size) {
        this.n = size;
        matrix = new int[n][n];
        used = new boolean[n*n+1];

        magicSum = n * (n*n + 1) /2;

        solutionCount = 0;
        Arrays.fill(used, false);
        for (int i = 0; i < n; i++) {
            Arrays.fill(matrix[i],0);
        }
    }

    public void print() {
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void solve() {
        long startTime = System.currentTimeMillis();
        attemp(0,0);
//        attempSimple(0,0);

        System.out.println("SOLUTIONS FOUND: " + solutionCount);
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    private void attempSimple(int i, int j) {
        if (i>=n || j >=n) {
            return;
        }

        for (int k = 1; k <=n*n; k++) {
            if (!used[k]) {
                used[k] = true;
                matrix[i][j] = k;

                if (i>=n-1 && j >=n-1 && valid()) {
                    solutionCount++;
                    print();
                }

                if (j >= n-1) {
                    attemp(i+1, 0);
                }
                else {
                    attemp(i, j+1);
                }

                matrix[i][j] = 0;
                used[k] = false;
            }
        }
    }

    private void attemp(int i, int j) {
//        if (solutionCount > 0) return;
        if (i>=n || j >=n) {
            return;
        }

        for (int k = 1; k <=n*n; k++) {
            if (!used[k] && possible(k, i, j)) {
                used[k] = true;
                matrix[i][j] = k;

//                if (i>=n-1 && j >=n-1 && valid()) {
                if (i>=n-1 && j >=n-1) {
                    solutionCount++;
                    print();
                }

                if (j >= n-1) {
                    attemp(i+1, 0);
                }
                else {
                    attemp(i, j+1);
                }

                matrix[i][j] = 0;
                used[k] = false;
            }
        }
    }

    private boolean valid() {
        int col = 0, row = 0, diagonal1 = 0, diagonal2 = 0;
        for (int i = 0; i < n; i ++) {
            row = 0;
            col = 0;
            diagonal1 += matrix[i][i];
            diagonal2 += matrix[i][n-1-i];

            for (int j = 0; j < n; j ++) {
                row += matrix[i][j];
                col += matrix[j][i];
            }

            if (row != magicSum || col != magicSum) return false;
        }

        if (diagonal1 != magicSum || diagonal2 != magicSum) return false;

        return true;
    }

    private boolean possible(int value, int i, int j) {
        int col = 0, row = 0, diagonal1 = 0, diagonal2 = 0;

        matrix[i][j] = value;

        for (int k = 0; k < n; k++) {
            row += matrix[i][k];
            col += matrix[k][j];
            diagonal1 += matrix[k][k];
            diagonal2 += matrix[k][n-1-k];
        }

        matrix[i][j] = 0;

        if (col > magicSum || row > magicSum || diagonal1 > magicSum || diagonal2 > magicSum) return false;

        int minRow = row;
        int maxRow = row;

        int minCol = col;
        int maxCol = col;

        int count = 1;
        for (int k = j+1; k<n; k++) {
            minRow += count;
            maxRow += n*n+1-count;
            count++;
        }

        count = 1;
        for (int k = i+1; k<n; k++) {
            minCol += count;
            maxCol += n*n+1-count;
            count++;
        }

        if (minCol > magicSum || maxCol < magicSum || minRow > magicSum || maxRow < magicSum) return false;

        if (i >= n-1 && (col != magicSum || diagonal2 != magicSum)) return false;
        if (j >= n-1 && row != magicSum) return false;
        if (i >= n-1 && j >= n-1 && diagonal1 != magicSum) return false;

        return true;
    }
}
