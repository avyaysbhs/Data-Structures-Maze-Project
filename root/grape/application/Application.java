package grape.application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import grape.Grape;
import grape.core.internal.graphics.Renderer2D;
import grape.core.internal.graphics.paint.DrawableNode;

public class Application<Engine extends Application.RenderingEngine> {
    private final String[] args;
    private Engine engine;

    public Application(String[] args, Class<? extends Engine> _class) {
        this.args = args;
    }

    public Application()
    {
        throw new UnsupportedOperationException();
    }

    static abstract class RenderingEngine extends DrawableNode {
        private Renderer2D renderer;

        public final void setRenderer(Renderer2D renderer) {
            if (this.renderer != null)
                throw new Grape.ConfigurationException("Renderer already exists for this engine instance!");
            this.renderer = renderer;
        };

        public abstract void render(Renderer2D renderer);
    }

    public final String[] getCmdParameters() {
        return args;
    }

    protected static final void launch(String[] args, Class<? extends Application> _app,
            Class<? extends Application.RenderingEngine> _engine_class) {
        try {
            Application app = _app.getConstructor(String[].class, _engine_class.getClass()).newInstance(args, _engine_class);
            for (Method m: _app.getMethods())
            {
                if (m.isAnnotationPresent(grape.application.lifecycle.Init.class) && m.getParameterCount() == 0)
                {
                    m.invoke(app);
                }
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }
}