package grape.core.internal.graphics.paint;

import java.awt.Graphics2D;

import grape.core.Node;
import grape.core.internal.graphics.Renderer2D;

public abstract class DrawableNode extends Node implements Drawable
{
    public final void renderChildren(Renderer2D renderer2d, Graphics2D g2d)
    {
        children.forEach(e ->
        {
            draw(e, renderer2d, g2d);
        });
    }

    private final static void draw(Node nd, Renderer2D renderer, Graphics2D g2d)
    {
        if (!(nd instanceof DrawableNode)) return;
        DrawableNode node = (DrawableNode) nd;
        node.render(renderer, g2d);
        node.children.forEach(e ->
        {
           draw(e, renderer, g2d); 
        });
    }
}