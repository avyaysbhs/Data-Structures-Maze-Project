import grape.core.internal.math.*;

public class UnitTest
{
    public static void main(String[] args)
    {
        float[][] a = {
            { 1, 2 },
            { 3, 4 },
        };

        float[][] b = {
            { 1, 3 },
            { 2, 1 }
        };

        MathFormat.print_mat(Matrix.add(Matrix.scale(Matrix.identity(2), 4), b), 3);
    }
}