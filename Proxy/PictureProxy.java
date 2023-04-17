public class PictureProxy implements Graphic {
    private String fileName;

    public PictureProxy (String fileName) {
        this.fileName = fileName;
    }

    public void draw(Point position) {
        // NaN
        return;
    }

    public Dimension getExtent() {
        return this.getPicture().getExtent();
    }

    public Point getPosition() {
        return this.getPicture().getPosition();
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

    public Picture getPicture() {
        return (new Picture(this.fileName));
    }
}


