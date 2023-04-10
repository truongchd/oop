import Expression.*;
import java.util.*;

public class Main {
    public static void main (String[] args) {
        // Numeral
        Expression a = new Numeral(12);
        System.out.println(a.toString());
        // Square
        Expression b = new Square(a);
        System.out.println(b.toString());
        System.out.println(b.evaluate());
        // Addition
        Expression c = new Addition(a, b);
        System.out.println(c.toString());
        System.out.println(c.evaluate());
    }
}
