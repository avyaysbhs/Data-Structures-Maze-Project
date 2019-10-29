package grape.core.internal.graphics.paint;

import java.awt.Graphics2D;

import grape.core.internal.graphics.Renderer2D;

public interface Drawable
{
    public void render(Renderer2D renderer2d, Graphics2D graphics);
}