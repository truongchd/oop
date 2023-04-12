import java.util.*;

abstract public class Region {
    private int[] grid;
    private HashSet<Integer> possible;

    public Region(int[] grid) {
        try {
            if (grid.length != 9) {
                throw new Exception("A grid must have length 9");
            }
            this.grid = Arrays.copyOfRange(grid, 0, 9);
            this.possible = new HashSet<>();
            for (int i = 1; i <= 9; i++) {
                this.possible.add(i);
            }

            for (int i = 0; i < this.grid.length; i++) {
                if (this.grid[i] == 0) continue;
                if (!this.possible.contains(this.grid[i])) {
                    throw new Exception("A grid cannot have repeated value");
                }
                this.possible.remove(this.grid[i]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        } 
    }

    public boolean modify(int pos, int new_val) {
        try {
            if (this.grid[pos] == new_val) {
                throw new Exception("New value must be different from old value");
            }

            if (new_val != 0 && !this.possible.contains(new_val)) {
                throw new Exception("New value must be different from the set");
            }

            if (new_val != 0) this.possible.remove(new_val);
            if (this.grid[pos] != 0) this.possible.add(this.grid[pos]);

            this.grid[pos] = new_val;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public HashSet<Integer> getPossibleValues () {
        return this.possible;
    }
}