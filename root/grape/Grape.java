package grape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Grape {
    public final static List<Entity> Entities = new ArrayList<>();
    private static Map<Class<? extends Component>, Map<Entity, List<Component>>> EntitiesByComponent = new HashMap<>();

    public Grape.Entity CreateEntity()
    {
        return new Entity();
        // TODO registration implementation
    }

    public static final class Entity
    {
        public final int id;

        Entity()
        {
            id = Entities.size();
            Entities.add(this);
        }

        private static void Verify(Entity entity, Class<? extends Component> componentClass)
        {
            Map<Entity, List<Component>> map = EntitiesByComponent.get(componentClass);

            if (map == null) {
                map = new HashMap<>();
                EntitiesByComponent.put(componentClass, map);
            }

            List<Component> components = map.get(entity);

            if (components == null) {
                components = new ArrayList<>();
                map.put(entity, components);
            }
        }

        public void AddComponent(Component component)
        {
            Verify(this, component.getClass());

            try
            {
                EntitiesByComponent.get(component.getClass()).get(this).add(component);
            }
            catch (Exception e)
            {
                throw new GrapeException(e.getMessage());
            }
        }

        public List<Component> GetComponents()
        {
            return new ArrayList<>(); // TODO FIX THIS
        }

        public <T extends Component> T GetComponent(Class<? extends T> componentClass)
        {
            for (Component c: GetComponents())
            {
                if (c.getClass() == componentClass)
                {
                    return (T) c;
                }
            }
            
            return null;
        }
    }

    public static abstract class Component
    {
        
    }

    public static abstract class System
    {
        public abstract void Update(Entity[] entities);
    }

    public Entity[] GetEntitiesBySystem(System sys)
    {
        List<Entity> entities = new ArrayList<>();

        // TODO implementation

        Entity[] _entities = new Entity[entities.size()];
        return entities.toArray(_entities);
    }

    private static class GrapeException extends RuntimeException
    {   
        private static final long serialVersionUID = 1L;

        public GrapeException(String message)
        {
            super("Grape Error -> " + message);
        }

        public void printStackTrace()
        {
            super.printStackTrace();
        }
    }

    static public final class ConfigurationException extends GrapeException
    {
        private static final long serialVersionUID = 1L;

        public ConfigurationException(String message)
        {
            super("Configuration -> " + message);
        }
    }
}