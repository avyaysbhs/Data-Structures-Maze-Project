package grape.core.internal.graphics.rendering;

import javax.swing.*;
import java.awt.*;
import grape.core.internal.math.*;

public abstract class AsyncRenderer extends JPanel implements Runnable
{
    private static final long serialVersionUID = 1L;

    private RendererState state = RendererState.Idle;
    private Thread runningThread = null;
    private long interval = 50;

    public AsyncRenderer()
    {
        super();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        setPreferredSize(screen);
        setMinimumSize(new Dimension(
            (int) (screen.getWidth() - 200),
            (int) (screen.getHeight() - 200)
        ));

        setMaximumSize(screen);
    }

    public void setRenderInterval(long _int)
    {
        interval = _int;
    }

    public void setRunningThread(Thread t)
    {
        if (runningThread == null || state == RendererState.Stopped)
            runningThread = t;
    }

    public Thread getRunningThread()
    {
        return runningThread;
    }
    
    public void setState(RendererState state)
    {
        this.state = state;
    }

    public RendererState getState()
    {
        return state;
    }

    public void run()
    {
        while (state == RendererState.Running)
        {
            try 
            {
                this.paintComponent(this.getGraphics());
                Thread.sleep(interval);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void StartRenderer(AsyncRenderer renderer)
    {
        renderer.setRunningThread(new Thread(renderer));
        renderer.setState(RendererState.Running);
        renderer.getRunningThread().start();
    }

    public static void StopRenderer(AsyncRenderer renderer)
    {
        try {
            renderer.setState(RendererState.Stopped);
            renderer.getRunningThread().join();
        } catch (InterruptedException e) { }
    }

    public abstract void draw(Graphics2D g);

    public final void paintComponent(Graphics g)
    {
        if (this.state == RendererState.Running && g instanceof Graphics2D)
            draw((Graphics2D) g);
    }
}