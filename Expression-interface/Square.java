import java.lang.*;

public class Square implements Expression {
    private Expression expression;
    
    public Square (Expression a) {
        this.expression = a;
    }

    // Expressions
    public String toString() {
        String res = "";
        res = this.expression.toString() + "^2";
        return res; 
    }

    public int evaluate() {
        int tmp = this.expression.evaluate();
        return tmp * tmp;
    }
}
