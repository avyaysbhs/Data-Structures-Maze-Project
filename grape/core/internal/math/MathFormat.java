package grape.core.internal.math;

import java.text.DecimalFormat;

public final class MathFormat
{
    private final static String MatrixFormatSpacing = "%10s";
    private final static DecimalFormat MatrixValueDecimalFormat = new DecimalFormat("#####################################0.###");

    public static void print_mat(float[][] m, String MatrixFormatSpacing)
    {
        for (int r = 0; r < m.length; r++)
        {
            System.out.print("[");

            for (int c = 0; c < m[r].length; c++)
            {
                System.out.print(String.format(MatrixFormatSpacing, MatrixValueDecimalFormat.format(m[r][c])));
            }

            System.out.println(String.format(MatrixFormatSpacing + "]", ""));
        }
    }

    public static void print_mat(float[][] m, int spacing)
    {
        for (int r = 0; r < m.length; r++)
        {
            System.out.print("[");

            for (int c = 0; c < m[r].length; c++)
            {
                System.out.print(String.format("%" + spacing + "s", MatrixValueDecimalFormat.format(m[r][c])));
            }

            System.out.println(String.format("%" + (spacing - 1) + "s" + "]", ""));
        }
    }

    public static void print_mat(float[][] m)
    {
        print_mat(m, 10);
    }
}