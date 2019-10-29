package grape.objects.general;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;

import grape.core.internal.graphics.Renderer2D;
import grape.core.internal.graphics.paint.Color3;
import grape.core.internal.graphics.paint.Drawable;
import grape.core.physics.PhysicalObject2D;
import grape.core.physics.Vector2;
import grape.core.shapes.Rectangle2D;

public class Block2D extends PhysicalObject2D implements Drawable
{
    private Rectangle2D rect;
    public Color3 color;
    public Color3 borderColor;
    public float borderWidth;

    public Block2D(Vector2 position, Vector2 size)
    {
        this.position = position;
        this.size = size;

        rect = new Rectangle2D(position, size);
    }

    public Block2D(Vector2 size)
    {
        this(new Vector2(), size);
    }

    public Block2D()
    {
        this(new Vector2(10, 10));
    }

    public void render(Renderer2D renderer, Graphics2D g2d)
    {
        java.awt.Shape shape = rect.ToPolygonAWT(renderer);
        g2d.setColor(Color3.toAWT(color));
        g2d.fill(shape);
        g2d.setColor(Color3.toAWT(borderColor));
        g2d.setStroke(new BasicStroke(borderWidth));
        g2d.draw(shape);
    }
}