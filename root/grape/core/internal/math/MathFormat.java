package grape.core.internal.math;

import java.text.DecimalFormat;

public final class MathFormat
{
    private final static DecimalFormat printf_mat_val_dec = new DecimalFormat("#####################################0.###");

    public static void print_mat(float[][] m, String matrix_format_spacing)
    {
        for (int r = 0; r < m.length; r++)
        {
            System.out.print("[");

            for (int c = 0; c < m[r].length; c++)
            {
                System.out.print(String.format(matrix_format_spacing, printf_mat_val_dec.format(m[r][c])));
            }

            System.out.println(String.format(matrix_format_spacing + "]", ""));
        }
    }

    public static void print_mat(float[][] m, int spacing)
    {
        for (int r = 0; r < m.length; r++)
        {
            System.out.print("[");

            for (int c = 0; c < m[r].length; c++)
            {
                System.out.print(String.format("%" + spacing + "s", printf_mat_val_dec.format(m[r][c])));
            }

            System.out.println(String.format("%" + (spacing - 1) + "s" + "]", ""));
        }
    }

    public static void print_mat(float[][] m)
    {
        print_mat(m, 10);
    }

    public static String str_mat(float[][] m, int spacing)
    {
        String out = "";
        for (int r = 0; r < m.length; r++)
        {
            out += "[";

            for (int c = 0; c < m[r].length; c++)
            {
                out += (String.format("%" + spacing + "s", printf_mat_val_dec.format(m[r][c])));
            }

            out += (String.format("%" + (spacing - 1) + "s" + "]", "")) + "\r\n";
        }

        return out;
    }

    public static String str_mat(float[][] m)
    {
        return str_mat(m, 10);
    }
}