package grape.application;

import javax.swing.JFrame;

public class Window extends JFrame
{
    public Window(int width, int height, boolean resizable)
    {
        super();
        setResizable(resizable);
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