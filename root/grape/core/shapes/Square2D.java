package grape.core.shapes;

import grape.core.physics.Vector2;

public class Square2D extends Rectangle2D
{
    public Square2D(Vector2 center, double size)
    {
        super(center, new Vector2(size, size));
    }
}