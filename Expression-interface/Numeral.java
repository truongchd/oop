import java.lang.*;

public class Numeral implements Expression {
    private int value;

    public Numeral (int a) {
        super(a);
        this.value = a;
    }

    // Expressions
    public String toString() {
        return Integer.toString(this.value); 
    }

    public int evaluate() {
        return this.value;
    }
}
