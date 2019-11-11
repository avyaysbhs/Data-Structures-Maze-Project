import javax.swing.JFrame;

import grape.application.Application;
import grape.application.GrapeEngine2D;
import grape.application.Window;
import grape.application.lifecycle.Init;
import grape.core.physics.Coordinate;

@Application.Configurations(
    Renderer = GrapeEngine2D.class,
    Dimensions = @Coordinate(
        X = 1080,
        Y = 720
    )
)
public class MazeGame extends Application
{
    @Init
    public void start()
    {
        System.out.println("Hello, world!");
        window().showView();
    }
    public static void main(String[] args)
    {
        launch(args, MazeGame.class);
    }
}