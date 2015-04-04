package GUITest;

import java.awt.geom.*;

public class Spotlight
{
    protected Ellipse2D.Double spot;
    protected Rectangle2D.Double bounds;

    public Spotlight(int x, int y, int w, int h)
    {
        this.spot = new Ellipse2D.Double(x, y, w, h);
    }

    public Ellipse2D getSpot()
    {
        return spot;
    }

    public double getArea()
    {
        return Math.PI * spot.getWidth() * spot.getHeight() / 4.0;
    }
}