import java.awt.*;

public class Picture implements Graphic {
    private String fileName;
    private Dimension extent;
    private Point position;
    private Picture picture;
    private String id;
    
    public void draw(Point position) {
        // NaN
        return;
    }

    public Dimension getExtent() {
        return this.extent;
    }

    public Point getPosition() {
        return this.position;
    }

    public void handleMouse(Event mouseEvent) {
        // NaN
        return;
    }

    public void store() {
        // NaN
        return;
    }

    public void load() {
        // NaN
        return;
    }
}
