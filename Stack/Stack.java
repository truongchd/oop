import java.util.*;

public class Stack<T> {
    private Vector<T> stack;

    public Stack() {
        this.stack = new Vector<T>();
    }

    public void push(T a){
        stack.add(a);
    }

    public T pop() {
        try {
            if (this.isEmpty()) {
                throw new Exception("The stack is empty!");
            }
            T ret = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            return ret;
        } catch (Exception e) {
            return (T)e.getMessage();
        }
    }

    public boolean isEmpty(){
        return stack.size() == 0;
    }
}