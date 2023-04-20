package Proxy;

import java.awt.*;

interface Graphic {
    void draw(Point position);
    public Dimension getExtent();
    public Point getPosition();
    public void handleMouse(Event mouseEvent); 
    public void store();
    public void load();
}