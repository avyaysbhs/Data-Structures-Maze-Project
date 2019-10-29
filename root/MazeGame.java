import grape.application.Application;
import grape.application.GrapeEngine2D;
import grape.application.Application.Configurations;
import grape.application.lifecycle.Init;

@Configurations(
    Renderer = GrapeEngine2D.class
)
public class MazeGame extends Application
{

    @Init
    public void start()
    {
        System.out.println("Hello, world!");
    }
    public static void main(String[] args)
    {
        launch(args, MazeGame.class);
    }
}