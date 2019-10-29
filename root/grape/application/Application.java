package grape.application;

import java.awt.Graphics2D;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import grape.Grape;
import grape.core.Node;
import grape.core.internal.Updateable;
import grape.core.internal.concurrent.*;
import grape.core.internal.graphics.Renderer2D;
import grape.core.internal.graphics.paint.DrawableNode;
import grape.core.internal.graphics.rendering.AsyncRenderer;

public class Application {

    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Configurations {
        Class<? extends RenderingEngine> Renderer();
    }

    private String[] args;
    protected final RenderingEngine RenderingEngine;

    public Application(RenderingEngine renderer) {
        RenderingEngine = renderer;
    }

    public void passParameters(String[] args) {
        if (this.args == null)
            this.args = args;
    }

    public Application() {
        throw new UnsupportedOperationException();
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

    protected static final void launch(String[] args, Class<? extends Application> _app) {
        try {
            RenderingEngine engine = _app.getAnnotation(Configurations.class).Renderer().newInstance();
            Application app = _app.getConstructor(RenderingEngine.class).newInstance(engine);
            app.passParameters(args);
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

    private static void initialize(Application app) {
        AsyncRenderer.StartRenderer(app.RenderingEngine.getRenderer());
        JobScheduler.main.async(new Job(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                    update(app.RenderingEngine);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }));
    }
}