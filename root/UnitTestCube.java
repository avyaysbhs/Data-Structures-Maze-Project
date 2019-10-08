import javax.swing.*;
import java.awt.*;

import grape.core.internal.concurrent.DispatchQueue;
import grape.core.internal.concurrent.Job;
import grape.core.internal.graphics.rendering.AsyncRenderer;
import grape.core.internal.math.*;

public class UnitTestCube
{
    public static AsyncRenderer panel = new AsyncRenderer() {
        public java.awt.Color _col;
        
        public void draw(Graphics2D g)
        {
            if (_col != null)
                g.setColor(_col);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    static {
        AsyncRenderer.StartRenderer(panel);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        DispatchQueue.main.async(new Job(() ->
        {
            Color _col = new Color();

            while (true)
            {
                panel.setColor(_col);
                _col = new Color(
                    _col.getRed() * 1 + (Math.random() * .2 - .2), 
                    _col.getGreen() * 1 + (Math.random() * .2 - .2), 
                    _col.getBlue() * 1 + (Math.random() * .2 - .2)
                );
            }
        }));
    }
}