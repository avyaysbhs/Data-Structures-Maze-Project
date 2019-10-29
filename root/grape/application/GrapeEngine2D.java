package grape.application;

import java.awt.Graphics2D;

import grape.core.internal.graphics.Renderer2D;
import grape.core.internal.math.MathOps;

class Engine2D extends Renderer2D
{
    private static final long serialVersionUID = 1L;
    private final GrapeEngine2D engine2d;
    private Graphics2D placeholderGraphics2d;

    public Engine2D(GrapeEngine2D engine2d)
    {
        this.engine2d = engine2d;
    }

    @Override
    public int vectorToPixelMagnitude(double v) {
        return MathOps.round(v * 10);
    }

    public Graphics2D getGraphics()
    {
        return placeholderGraphics2d;
    }

    public void draw(Graphics2D graphics)
    {
        this.placeholderGraphics2d = graphics;
        engine2d.render(this, graphics);
    }
}

public class GrapeEngine2D extends Application.RenderingEngine
{
    public GrapeEngine2D()
    {
        super();
        setRenderer(new Engine2D(this));
    }

    public void render(Renderer2D renderer2d, Graphics2D graphics2d)
    {
        this.renderChildren(renderer2d, graphics2d);
    }
}