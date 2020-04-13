package twofour;

public class Sample {
    Node[] tree;

    private class Node {
        double val;
        double sum;

        public Node(double val) {
            this.val = val;
        }
    }

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            int[] countRow = new int[256];
            int[] countColumn = new int[256];
            int[] countSquare = new int[256];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && ++countRow[board[i][j]] > 1)
                    return false;
                if (board[j][i] != '.' && ++countColumn[board[j][i]] > 1)
                    return false;
                if (board[j / 3 + 3 * (i / 3)][j % 3 + 3 * (i % 3)] != '.'
                        && ++countSquare[board[j / 3 + 3 * (i / 3)][j % 3 + 3 * (i % 3)]] > 1)
                    return false;
            }
        }
        return true;
    }

    public Sample(double[] probabilities) {
        tree = new Node[probabilities.length + 1];
        for (int i = 0; i < probabilities.length; i++) {
            tree[i + 1] = new Node(probabilities[i]);
        }

        for (int i = probabilities.length / 2; i >= 1; i--) {

        }
    }

}
