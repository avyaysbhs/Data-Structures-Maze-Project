import java.io.*;
import java.util.ArrayList;

public class Test
{
    public static void main(String[] args)
    {
        int[][] walls;
        ArrayList<String> lines = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(new File("levels/level1.txt")));
            String t;
            String out = "";
            while ((t = in.readLine()) != null)
            {
                lines.add(t);
            }
            walls = new int[lines.size()][lines.get(0).length()];
            for (int i=0;i<lines.size();i++)
            {
                for (int j=0;j<lines.get(i).length();j++)
                {
                    walls[i][j] = lines.get(i).charAt(j) == '*' ? 1 : 0;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}