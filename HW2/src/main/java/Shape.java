import java.util.*;

/*
 Shape data for ShapeClient:
 "0 0  0 1  1 1  1 0"
 "10 10  10 11  11 11  11 10"
 "0.5 0.5  0.5 -10  1.5 0"
 "0.5 0.5  0.75 0.75  0.75 0.2"
*/ 
public class Shape {
    private List<Point> points = new ArrayList();
    private Point center;

    public Shape(String s) {
        // init values + center
        double centerX = 0, centerY = 0;

        String[] numStr = s.split(" ", 8);
        for (int i = 0; i < numStr.length; i += 2) {
            Point middleWare = new Point(Double.parseDouble(numStr[i]), Double.parseDouble(numStr[i + 1]));
            this.points.add(middleWare);
            centerX += Double.parseDouble(numStr[i]);
            centerY += Double.parseDouble(numStr[i + 1]);
        }

        centerX /= 4;
        centerY /= 4;
        this.center = new Point(centerX, centerY);
    }
    
    public Point getCenter() {
        return this.center;
    }

    public double getRadius() {
        double minValue = -1, summa;
        for (Point p: this.points) {
            summa = this.center.distance(p);
            if (minValue == -1.0 || minValue < summa) {
                minValue = summa;
            }
        }
        return minValue;
    }
    
    public boolean checkCross(Shape other){
        double summa, thisRad = this.getRadius();
        boolean more = false, less = false;
        for(Point p: other.points) {
            summa = p.distance(this.center);
            if (summa > thisRad) {
                more = true;
            } else {
                less = true;
            }
        }
        return (more && less);
    }

    public int checkEncircle(Shape other) {
        double ourRadius = this.getRadius(), theirRadius = other.getRadius();
        double centerDist = this.center.distance(other.getCenter());
        if (centerDist < ourRadius) {
            return 2;
        } else if (centerDist < ourRadius + theirRadius) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {

        Shape a = new Shape("0 0 0 1 1 1 1 0");
        Shape b = new Shape("10 10 10 11 11 11 11 10");
        Shape c = new Shape("0.5 0.5 0.5 -10 1.5 0");
        Shape d = new Shape("0.5 0.5 0.75 0.75 0.75 0.2");

        System.out.println(a.checkCross(b));
        System.out.println(a.checkCross(c));
        System.out.println(a.checkCross(d));

        System.out.println(a.checkEncircle(b));
        System.out.println(a.checkEncircle(c));
        System.out.println(a.checkEncircle(d));
    }
}

