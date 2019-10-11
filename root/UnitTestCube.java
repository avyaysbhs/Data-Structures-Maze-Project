import javax.swing.*;
import java.awt.*;

import grape.core.internal.concurrent.JobScheduler;
import grape.core.internal.concurrent.Job;
import grape.core.internal.graphics.paint.Color3;
import grape.core.internal.graphics.rendering.AsyncRenderer;
import grape.core.internal.math.*;

public class UnitTestCube
{
    static Color3 color = new Color3(.7,.6,.7);

    public static AsyncRenderer panel = new AsyncRenderer() {
        public void draw(Graphics2D g)
        {
            if (color != null)
                g.setColor(Color3.toAWT(color));
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

        JobScheduler.main.async(new Job(() ->
        {
            double rad = 5;
            while (true)
            {
                try
                {
                    Thread.sleep(50);
                } catch (Exception e)
                {}
                finally
                {
                    System.out.println(color);
                }

                color.set(
                    color.r * (1 + Math.random()/100 - .005),
                    color.g * (1 + Math.random()/100 - .005),
                    color.b * (1 + Math.random()/100 - .005));
            }
        }));
    }

    public static void set_color(Color3 c)
    {
        color = c;
    }
}