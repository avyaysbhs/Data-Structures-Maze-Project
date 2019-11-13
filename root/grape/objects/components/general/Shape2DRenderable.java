package grape.objects.components.general;

import grape.Grape;
import grape.core.internal.graphics.paint.Color3;
import grape.core.shapes.Shape2D;

public class Shape2DRenderable extends Grape.Component
{
    public Shape2D Shape;
    public Color3 StrokeColor;
    public Color3 FillColor;
    public double StrokeWidth;
}