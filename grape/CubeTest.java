import javax.swing.*;
import java.awt.*;

import grape.core.internal.graphics.rendering.AsyncRenderer;
import grape.core.internal.math.*;

public class CubeTest
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        AsyncRenderer panel = new AsyncRenderer() {
            public void draw(Graphics2D g)
            {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        AsyncRenderer.StartRenderer(panel);
        
        frame.add(panel);
        frame.setVisible(true);
    }
}