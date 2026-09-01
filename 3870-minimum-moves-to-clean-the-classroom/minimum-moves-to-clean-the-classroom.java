import java.util.*;

class Solution {

    static class State {
        int row;
        int col;
        int mask;
        int energy;
        int moves;

        State(int row, int col, int mask, int energy, int moves) {
            this.row = row;
            this.col = col;
            this.mask = mask;
            this.energy = energy;
            this.moves = moves;
        }
    }

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startRow = -1;
        int startCol = -1;

        // litterId[r][c] = ID of litter at this cell
        int[][] litterId = new int[m][n];

        for (int r = 0; r < m; r++) {
            Arrays.fill(litterId[r], -1);
        }

        int litterCount = 0;

        // Find S and assign IDs to L
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    startRow = r;
                    startCol = c;
                }

                if (ch == 'L') {
                    litterId[r][c] = litterCount;
                    litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        int totalMasks = 1 << litterCount;
        int fullMask = totalMasks - 1;

        /*
         * visited[r][c][mask]
         * = maximum energy with which we have reached
         *   (r, c) after collecting 'mask'
         */
        int[][][] visited = new int[m][n][totalMasks];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(visited[r][c], -1);
            }
        }

        Queue<State> queue = new ArrayDeque<>();

        queue.offer(new State(
                startRow,
                startCol,
                0,
                energy,
                0
        ));

        visited[startRow][startCol][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            State curr = queue.poll();

            int r = curr.row;
            int c = curr.col;
            int mask = curr.mask;
            int currEnergy = curr.energy;
            int moves = curr.moves;

            // All litter collected
            if (mask == fullMask) {
                return moves;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                // Cannot make a move without energy
                if (currEnergy == 0) {
                    continue;
                }

                // Moving costs 1 energy
                int newEnergy = currEnergy - 1;

                int newMask = mask;

                char nextCell = classroom[nr].charAt(nc);

                // Collect litter
                if (nextCell == 'L') {
                    int id = litterId[nr][nc];
                    newMask |= (1 << id);
                }

                // Reset energy on R
                if (nextCell == 'R') {
                    newEnergy = energy;
                }

                /*
                 * If energy becomes 0, we can continue ONLY if
                 * we are standing on R.
                 *
                 * BUT if this move collected the final litter,
                 * it is valid even if energy becomes 0.
                 */
                if (newEnergy == 0 &&
                    nextCell != 'R' &&
                    newMask != fullMask) {
                    continue;
                }

                /*
                 * If we have already reached this same
                 * (row, col, mask) with MORE energy,
                 * this state is useless.
                 */
                if (visited[nr][nc][newMask] >= newEnergy) {
                    continue;
                }

                visited[nr][nc][newMask] = newEnergy;

                queue.offer(new State(
                        nr,
                        nc,
                        newMask,
                        newEnergy,
                        moves + 1
                ));
            }
        }

        return -1;
    }
}