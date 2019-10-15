package grape.core.internal.graphics;

import grape.core.internal.graphics.rendering.*;
import grape.core.physics.Vector2;
import java.awt.event.*;

public abstract class Renderer2D extends AsyncRenderer implements KeyListener
{
    public abstract int vectorToPixelMagnitude(double v);
    public abstract void keyPressed(KeyEvent e);

    public void draw(java.awt.Graphics2D g)
    {
        
    }

    public int[] calculatePixel(Vector2 v)
    {
        int centerX = getWidth() % 2 == 0 ? getWidth()/2 : getWidth()/2 + 1;
        int centerY = getHeight() % 2 == 0 ? getHeight()/2 : getHeight()/2 - 1;
        
        int[] delta = calculatePixelMagnitude(v);
        int[] out = {
            centerX + delta[0],
            centerY - delta[1]
        };

        return out;
    }

    public int[] calculatePixelMagnitude(Vector2 v)
    {
        double dx = v.x, dy = v.y;
        
        int[] out = {
            vectorToPixelMagnitude(dx),
            vectorToPixelMagnitude(dy)
        };
        
        return out;
    }
}