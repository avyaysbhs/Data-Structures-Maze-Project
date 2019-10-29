package grape.core.shapes;

import grape.core.internal.graphics.Renderer2D;
import grape.core.physics.Vector2;
import java.awt.Polygon;

public class Shape2D
{
    Vector2[] points;

    public Shape2D(Vector2... points)
    {
        this.points = points;
    }

    public static Polygon ToPolygonAWT(Shape2D s, Renderer2D renderer)
    {
        int[] xpoints = new int[s.points.length];
        int[] ypoints = new int[s.points.length];

        for (int i=0;i<s.points.length;i++)
        {
            int[] p = renderer.calculatePixel(s.points[i]);
            xpoints[i] = p[0];
            ypoints[i] = p[1];
        }

        Polygon p = new Polygon(xpoints, ypoints, s.points.length);
        return p;
    }

    public Polygon ToPolygonAWT(Renderer2D renderer)
    {
        return ToPolygonAWT(this, renderer);
    }
}