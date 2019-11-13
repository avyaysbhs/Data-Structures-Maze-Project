import java.io.*;
import java.util.*;

public class ClearTextPad
{
    public static void main(String[] args) {
        Collection<File> all = new ArrayList<File>();
        addTree(new File("./root"), all);
        for (File f: all)
        {
            if (f.getName().contains(".class") || (f.getName().contains("tp") && f.getName().contains(".BAT")))
            {
                System.out.println(f);
                f.delete();
            }
        }
    }
    
    static void addTree(File file, Collection<File> all) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                all.add(child);
                addTree(child, all);
            }
        }
    }
    
}