public class Addition extends BinaryExpression {
    private Expression left;
    private Expression right;

    public Addition(Expression a, Expression b) {
        super();
        left = a;
        right = b;
    }
}
