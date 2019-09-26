package grape.core.internal.math;

public final class Matrix
{
    public static float[][] identity(int size)
    {
        float[][] out = new float[size][size];

        for (int i=0;i<size;i++)
        {
            out[i][i] = 1;
        }

        return out;
    }

    public static float[][] add(float[][] a, float[][] b)
    {
        if (a.length < 1) return null;
        if (a.length != b.length) return null;
        if (a[0].length != b[0].length) return null;

        float[][] out = new float[a.length][a[0].length];

        for (int i=0; i<out.length; i++)
        {
            for (int j=0;j<out[i].length;j++)
            {
                out[i][j] = a[i][j] + b[i][j];
            }
        }

        return out;
    }

    public static float[][] inverse_mat(float[][] input)
    {
        float[][] out = new float[input.length][input[0].length];


        
        return out;
    }

    public static float[][] scale(float[][] m, float s)
    {
        float[][] out = new float[m.length][m[0].length];
        
        for (int i=0;i<m.length;i++)
        {
            for (int j=0;j<m[0].length;j++)
            {
                out[i][j] = m[i][j] * s;
            }
        }

        return out;
    }

    public static float[][] dot_mat(float[][] a, float[][] b)
    {
        if (a.length < 1 || b.length < 1) return null;

        /***
         * r_a: Rows of float[][] a: 
         *  *int
         * r_b: Rows of float[][] b: 
         *  *int
         * c_a: Columns of float[][] a:
         *  *int
         * c_b: Columns of float[][] b:
         *  *int
         */
        
        int r_a = a.length, c_a = a[0].length;
        int r_b = b.length, c_b = b[0].length;

        float[][] out = new float[r_a][c_b];

        for (int i = 0; i < r_a; i++) {
            for (int j = 0; j < c_b; j++) {
                for (int k = 0; k < c_a; k++) {
                    out[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return out;
    }
}