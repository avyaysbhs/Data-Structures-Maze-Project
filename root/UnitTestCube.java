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
import grape.core.shapes.Shape2D;

public class UnitTestCube
{
    static Color3 color = new Color3(.7,.6,.7);
    static Vector2 VECTOR = new Vector2();

    public static Renderer2D panel = new Renderer2D() {
        private static final long serialVersionUID = 1L;

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

            Shape2D s = new Shape2D(
                new Vector2(-4, 0),
                new Vector2(2, 8),
                new Vector2(4, 0)
            );

            g.setColor(Color.black);
            g.fill(Shape2D.ToPolygonAWT(s, this));
        }
    };

    static {
        AsyncRenderer.StartRenderer(panel);
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.addKeyListener(panel);

        panel.event.on("key", key ->
        {
            System.out.println(key.get("char"));
        });

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
                    color.r * (1 + Math.random()/100 - .015),
                    color.g * (1 + Math.random()/100 - .015),
                    color.b * (1 + Math.random()/100 - .015));
            }
        }));
    }

    public static void set_color(Color3 c)
    {
        color = c;
    }
}