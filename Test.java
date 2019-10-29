import java.io.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        int[][] walls = null;
        ArrayList<String> lines = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(new File("root/levels/level1.txt")));
            String t;
            String out = "";
            while ((t = in.readLine()) != null) {
                lines.add(t);
            }
            walls = new int[lines.size()][lines.get(0).length()];
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    walls[i][j] = lines.get(i).charAt(j) == '*' ? 1 : 0;
                    if (lines.get(i).charAt(j) == '.')
                        walls[i][j] = 2;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls[i].length; j++) {
                int temp = walls[i][j / 3];
                walls[i][j / 3] = walls[j / 3][i];
                walls[j / 3][i] = temp;
            }
        }

        try {
            PrintStream stream = new PrintStream(new File("root/levels/level2.txt"));
            for (int i=0;i<walls.length;i++)
            {
                for (int j=0;j<walls[i].length;j++)
                {
                    stream.print(walls[i][j] == 1 ? "*" : (walls[i][j] == 2 ? "." : " "));
                }
                stream.println();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}