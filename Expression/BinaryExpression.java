abstract public class BinaryExpression extends Expression {
    abstract public Expression left();
    abstract public Expression right();

    public BinaryExpression() {
        super();
    }
}