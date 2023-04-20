package Expression;

public class Division extends BinaryExpression {
    private Expression left;
    private Expression right;

    public Division(Expression a, Expression b) {
        this.left = a;
        this.right = b;
    }

    // Expressions
    public String toString() {
        String res = "";
        res = "(" + this.left.toString() + "/" + this.right.toString() + ")";
        return res; 
    }

    public int evaluate() {
        return (int)(this.left.evaluate() / this.right.evaluate());
    }

    // Binary Expression
    public Expression left() {
        return this.left;
    }

    public Expression right() {
        return this.right;
    }
}
