import java.util.Set;

abstract public class Region {
    abstract public boolean checkValid();
    abstract public Set<Integer> getPossibleValues();
    abstract public void printGrid();
}
