package grape.application;

import javax.swing.JFrame;

public class Window extends JFrame
{
    public Window(int width, int height)
    {
        super();
        setResizable(false);
        setSize(width, height);
        setVisible(true);
    }

    public final void showView() { 
        setVisible(true); 
    }

    public final void hideView() {
        setVisible(false);
    }
}