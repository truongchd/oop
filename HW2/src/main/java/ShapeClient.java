import java.util.*;

public class ShapeClient {
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        String s_a = scan.nextLine();
        String s_b = scan.nextLine();
        String s_c = scan.nextLine();
        String s_d = scan.nextLine();
        scan.close();
        Shape a = new Shape(s_a);
        Shape b = new Shape(s_b);
        Shape c = new Shape(s_c);
        Shape d = new Shape(s_d);
        System.out.println("a crosses b:" + a.checkCross(b));
        System.out.println("a crosses c:" + a.checkCross(c));
        System.out.println("a crosses d:" + a.checkCross(d));
        System.out.println("a encircles b:" + a.checkEncircle(b));
        System.out.println("a encircles c:" + a.checkEncircle(c));
        System.out.println("a encircles d:" + a.checkEncircle(d));
    }
}