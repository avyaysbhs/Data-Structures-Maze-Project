package grape.application;

import java.awt.Graphics2D;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;

import grape.Grape;
import grape.core.internal.concurrent.*;
import grape.core.internal.graphics.Renderer2D;
import grape.core.internal.graphics.rendering.AsyncRenderer;
import grape.core.physics.Coordinate;

public class Application {

    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Configurations {
        Class<? extends Grape.Component>[] Components();

        Class<? extends Grape.System>[] Systems();

        WindowConfigurations Window() default @WindowConfigurations(Dimensions = @Coordinate(X = 720, Y = 480));
    }

    @Retention(RetentionPolicy.RUNTIME)
    public static @interface WindowConfigurations {
        Coordinate Dimensions();

        boolean Resizable() default true;
    }

    private Configurations configurations;
    private String[] args;
    private Window _window;

    public Application() {

    }

    public Window window() {
        return _window;
    }

    public void passParameters(String[] args) {
        if (this.args == null)
            this.args = args;
    }

    public void passConfigurations(Configurations config) {
        if (configurations == null) {
            configurations = config;
            _window = new Window(config.Window().Dimensions().X(), config.Window().Dimensions().Y(),
                    config.Window().Resizable());
            _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public final String[] getParameters() {
        return args;
    }

    protected static final void launch(String[] args, Class<? extends Application> _app) {
        try {
            if (!_app.isAnnotationPresent(Configurations.class))
                throw new Grape.ConfigurationException("No configurations present!");
            Constructor<Application> constructor = (Constructor<Application>) _app.getConstructor();
            Application app = constructor.newInstance();
            app.passParameters(args);
            app.passConfigurations(_app.getAnnotation(Configurations.class));
            for (Method m : _app.getMethods()) {
                if (m.isAnnotationPresent(grape.application.lifecycle.Init.class)
                        && m.getParameterCount() == 0) {
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

    private static void initialize(Application app) {
        //AsyncRenderer.StartRenderer(app.getRenderingEngine().getRenderer());
        //JobScheduler.main.async(new Job(new ApplicationRunner(app)));
    }
    
}