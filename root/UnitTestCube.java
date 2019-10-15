import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;

import grape.core.internal.concurrent.JobScheduler;
import grape.core.internal.concurrent.Job;
import grape.core.internal.graphics.Renderer2D;
import grape.core.internal.graphics.paint.Color3;
import grape.core.internal.graphics.rendering.AsyncRenderer;
import grape.core.internal.math.*;
import grape.core.physics.Vector2;

public class UnitTestCube
{
    static Color3 color = new Color3(.7,.6,.7);
    static Vector2 VECTOR = new Vector2();

    public static AsyncRenderer panel = new Renderer2D() {

        private void writeObject(ObjectOutputStream s) throws IOException
        {
            s.defaultWriteObject();
        }

        private void readObject(ObjectInputStream s) throws IOException
        {
            try {
                s.defaultReadObject();
            } catch (ClassNotFoundException e)
            {
                
            }
        }

        public void keyPressed(java.awt.event.KeyEvent e)
        {
            if (e.getKeyCode() == 37)
            {
                VECTOR.x--;
            }
        }
    
        @Override
        public int vectorToPixelMagnitude(double v) {
            return MathOps.round(v * 10);
        }

        public void draw(Graphics2D g)
        {
            if (color != null)
                g.setColor(Color3.toAWT(color));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color3.toAWT(new Color3(.4, .4, .4)));
            Vector2 left = VECTOR.subtract(1, 1);
            Vector2 right = VECTOR.add(2, 2);

            System.out.println(left + " " + right);

            int[] n = calculatePixel(left);
            int[] n2 = calculatePixelMagnitude(right);
            System.out.println(Arrays.toString(n));
            System.out.println(Arrays.toString(n2));
            g.setColor(Color.black);
            g.fillRect(n[0], n[1], n2[0], n2[1]);
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
                {}/*
                finally
                {
                    System.out.println(color);
                }*/

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