package grape.objects.systems.general;

import grape.Grape;
import grape.application.ecs.GrapeSystem;
import grape.core.shapes.Shape2D;
import grape.objects.components.general.Shape2DRenderable;
import grape.objects.components.general.Transform2D;

@GrapeSystem(
    Components = { Transform2D.class, Shape2DRenderable.class }
)
public class Transform2DRenderer extends Grape.System
{
    public void Update(Grape.Entity[] entities)
    {
        for (Grape.Entity e: entities)
        {
            Shape2DRenderable shape2d = e.GetComponent(Shape2DRenderable.class);
            // TODO DRAW
        }
    }
}