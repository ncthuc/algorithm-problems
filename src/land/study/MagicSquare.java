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
        attemp(0,0);

        System.out.println("SOLUTIONS FOUND: " + solutionCount);
    }

    private void attemp(int i, int j) {
//        System.out.println("attemp " + i + "," + j);
//        if (solutionCount > 0) return;
        if (i>=n || j >=n) {
            return;
        }

        for (int k = 1; k <=n*n; k++) {
            if (!used[k] && valid(k, i, j)) {
                used[k] = true;
                matrix[i][j] = k;
//                System.out.println("set " + i + "," + j + " = " + k);

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

    private boolean valid(int value, int i, int j) {
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

        if (i >= n-1 && (col != magicSum || diagonal2 != magicSum)) return false;
        if (j >= n-1 && row != magicSum) return false;
        if (i >= n-1 && j >= n-1 && diagonal1 != magicSum) return false;

        return true;
    }
}
