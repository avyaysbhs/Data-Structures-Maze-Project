import grape.application.Application;
import grape.application.RenderingEngine2D;

public class MazeGame extends Application<RenderingEngine2D>
{
    public static void main(String[] args)
    {
        launch(args, MazeGame.class, RenderingEngine2D.class);
    }
}