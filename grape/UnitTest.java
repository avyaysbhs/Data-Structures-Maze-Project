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

        float[][] m = Matrix.add(Matrix.scale(Matrix.identity(2), 4), b);

        MathFormat.print_mat(m, 3);

        //m = Matrix.identity3d();
        m = Matrix.identity(2);

        System.out.println("Cross: " + Matrix.cross2d(m));
    }
}