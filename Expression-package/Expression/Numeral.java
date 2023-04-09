package Expression;
import java.lang.*;

public class Numeral extends Expression {
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
