public class Main {
    public static void main (String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("Hello");
        stack.push("Hola");


        System.out.println((String)stack.pop());
        System.out.println(stack.isEmpty());
    }
}
