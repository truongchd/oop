import java.util.*;

public class Stack {
    private Vector<String> stack = new Vector<String>();

    public void push(String a){
        stack.add(a);
    }

    public String pop() {
        try {
            if (this.isEmpty()) {
                throw new Exception("The stack is empty!");
            }
            String ret = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            return ret;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public boolean isEmpty(){
        return stack.size() == 0;
    }
    public static void main (String[] args) {
        Stack stack = new Stack();
        stack.push("Hello");

        String s = stack.pop();
        System.out.println(s);
        System.out.println(stack.isEmpty());
    }
}
