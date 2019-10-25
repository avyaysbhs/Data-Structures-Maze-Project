package grape.core.internal.graphics.paint;

import grape.core.Node;
import grape.core.internal.graphics.Renderer2D;

public abstract class DrawableNode extends Node implements Drawable
{
    public abstract void render(Renderer2D renderer);
    public final void renderChildren(Renderer2D renderer2d)
    {
        children.forEach(e ->
        {
            draw(e, renderer2d);
        });
    }

    private final void draw(Node nd, Renderer2D renderer)
    {
        if (!(nd instanceof DrawableNode)) return;
        DrawableNode node = (DrawableNode) nd;
        node.render(renderer);
        node.children.forEach(e ->
        {
           draw(e, renderer); 
        });
    }
}