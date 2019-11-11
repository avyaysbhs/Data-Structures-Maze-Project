package grape.application;

import java.awt.Graphics2D;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;

import grape.Grape;
import grape.core.Node;
import grape.core.internal.Updateable;
import grape.core.internal.concurrent.*;
import grape.core.internal.graphics.Renderer2D;
import grape.core.internal.graphics.paint.DrawableNode;
import grape.core.internal.graphics.rendering.AsyncRenderer;
import grape.core.physics.Coordinate;

public class Application {

    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Configurations {
        Class<? extends RenderingEngine> Renderer() default GrapeEngine2D.class;
        Coordinate Dimensions();
    }

    private Configurations configurations;
    private String[] args;
    private RenderingEngine RenderingEngine;
    private Window _window;

    public Application()
    {

    }

    public Window window() { return _window; }

    public void passParameters(String[] args) {
        if (this.args == null)
            this.args = args;
    }

    public void passConfigurations(Configurations config)
    {
        if (configurations == null) {
            configurations = config;
            _window = new Window(config.Dimensions().X(), config.Dimensions().Y());
            _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    static abstract class RenderingEngine extends DrawableNode {
        private Renderer2D renderer;

        public final void setRenderer(Renderer2D renderer) {
            if (this.renderer != null)
                throw new Grape.ConfigurationException("Renderer already exists for this engine instance!");
            this.renderer = renderer;
        };

        public Renderer2D getRenderer() {
            return renderer;
        }

        public abstract void render(Renderer2D renderer, Graphics2D graphics2d);
    }

    public final String[] getParameters() {
        return args;
    }

    public final void setRenderer(RenderingEngine renderer)
    {
        if (RenderingEngine == null)
            RenderingEngine = renderer;
    }

    protected static final void launch(String[] args, Class<? extends Application> _app) {
        try {
            if (!_app.isAnnotationPresent(Configurations.class)) throw new Grape.ConfigurationException("No configurations present!");
            RenderingEngine engine = _app.getAnnotation(Configurations.class).Renderer().newInstance();
            Constructor<Application> constructor = (Constructor<Application>) _app.getConstructor();
            Application app = constructor.newInstance();
            app.setRenderer(engine);
            app.passParameters(args);
            app.passConfigurations(_app.getAnnotation(Configurations.class));
            for (Method m : _app.getMethods()) {
                if (m.isAnnotationPresent(grape.application.lifecycle.Init.class) && m.getParameterCount() == 0) {
                    m.invoke(app);
                }
            }
            initialize(app);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

    protected static final void launch(Class<? extends Application> _app)
    {
        launch(new String[0], _app);
    }

    public final RenderingEngine getRenderingEngine()
    {
        return RenderingEngine;
    }

    private static void update(Node node)
    {
        node.children.forEach(e ->
        {
            if (e instanceof Updateable)
            {
                e.children.forEach(Application::update);
                ((Updateable) e).update();
            }
        });
    }

    private static class ApplicationRunner implements Runnable
    {
        private boolean running = true;
        private Application app;

        public ApplicationRunner(Application app)
        {
            this.app = app;
        }

        public void run()
        {
            while (running) {
                try {
                    Thread.sleep(50);
                    update(app.getRenderingEngine());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stop()
        {
            running = false;
        }
    }

    private static void initialize(Application app) {
        AsyncRenderer.StartRenderer(app.getRenderingEngine().getRenderer());
        JobScheduler.main.async(new Job(new ApplicationRunner(app)));
    }
    
}