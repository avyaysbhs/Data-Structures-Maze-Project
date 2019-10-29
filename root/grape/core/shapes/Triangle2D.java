package grape.core.shapes;

import grape.core.physics.Vector2;

public class Triangle2D extends Shape2D
{
    public Triangle2D(
        Vector2 leftmost,
        Vector2 middle,
        Vector2 rightmost
    )
    {
        super(new Vector2[] { leftmost, middle, rightmost });
    }
}