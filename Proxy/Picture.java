package Proxy;

import java.awt.*;

class Picture implements Graphic {
    protected String fileName;
    protected Dimension extent;
    protected Point position;
    
    Picture(String fileName) {
        // load information about that Picture fileName
        this.fileName = fileName;
    }

    public void draw(Point position) {
        return;
    }

    public Dimension getExtent() {
        return this.extent;
    }

    public Point getPosition() {
        return this.position;
    }

    public void handleMouse(AWTEvent mouseEvent) {
        return;
    }

    public void store() {
        return;
    }

    public void load() {
        return;
    }
}