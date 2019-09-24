public final class Algebra
{
    public static float[][] mult_mat(float[][] a, float[][] b)
    {
        if (a.length < 1) return a;
        if (b.length < 1) return b;

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

    public static void main(String[] args)
    {
        float[][] a = {
            { 3, 4, 2 }
        };

        float[][] b = {
            { 13, 9, 7 , 15 },
            { 8, 7, 4, 6 },
            { 6, 4, 0, 3 }
        };

        print_mat(mult_mat(a, b));
    }

    public static void print_mat(float[][] m)
    {
        for (int r = 0; r < m.length; r++)
        {
            System.out.print("[");

            for (int c = 0; c < m[r].length; c++)
            {
                if (c < m[r].length - 1)
                {
                    System.out.print(String.format("%25f", m[r][c]));
                }
            }

            System.out.println("\t\t]");
        }
    }
}