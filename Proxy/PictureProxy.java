package Proxy;

public class PictureProxy implements Graphic {
    private Picture picture;

    public PictureProxy (String fileName) {
        this.picture = new Picture(fileName);
    }

    public void draw(Point position) {
        // NaN
        return;
    }

    public Dimension getExtent() {
        return this.picture.extent;
    }

    public Point getPosition() {
        return this.picture.position;
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

    protected Picture getPicture(String fileName) {
        return this.picture;
    }
}


