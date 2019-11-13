import grape.application.*;
import grape.application.lifecycle.Init;
import grape.core.internal.graphics.paint.Color3;
import grape.core.physics.Coordinate;
import grape.core.physics.Vector2;

import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

@Application.Configurations(
    Components = {}, Systems = {}, 
    Window = @Application.WindowConfigurations(
        Resizable = false, 
        Dimensions = @Coordinate(X = 720, Y = 720)
    )
)
public class MazeGame extends Application implements KeyListener {
    public int currentLevelID = 1;
    private int unlockedLevelID = 1;
    public int moves = 0;
    public Wall[][] currentLevel = new Wall[15][15];
    public Explorer explorer;
    public Location end;
    public final int lastLevelID = 3;
    private boolean disabled = false;
    private boolean is3d = false;

    public void read_to_walls(int level) {
        int[][] walls = null;
        ArrayList<String> lines = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(new File("root/levels/level" + level + ".txt")));
            String t;
            String out = "";
            while ((t = in.readLine()) != null) {
                lines.add(t);
            }
            walls = new int[lines.size()][lines.get(0).length()];
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    char ch = lines.get(i).charAt(j);
                    walls[i][j] = ch == '#' ? 1 : 0;

                    if (ch == 'S')
                        walls[i][j] = 2;
                    else if (ch == 'E')
                        walls[i][j] = 3;

                    System.out.print(walls[i][j]);
                }
                System.out.println();
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (walls[i][j] == 1)
                        currentLevel[i][j] = new Wall(i, j);
                    else if (walls[i][j] == 0)
                        currentLevel[i][j] = null;
                    else if (walls[i][j] == 2)
                    {
                        int direction = -1;
                        if (explorer != null)
                            direction = explorer.d();
                        explorer = new Explorer(i, j, direction);
                        currentLevel[i][j] = null;
                    }
                    else if (walls[i][j] == 3)
                    {
                        end = new Location(i, j);
                        currentLevel[i][j] = null;
                    }
                }
            }
        }
    }

    @Init
    public void start() {
        System.out.println("Hello, world!");
        menu();
        read_to_walls(currentLevelID);
        window().showView();
        window().addKeyListener(this);
        redraw();
    }

    public void redraw2D() {
        Graphics2D gfx = (Graphics2D) window().getGraphics();
        for (int r = 0; r < 15; r++) {
            for (int c = 0; c < 15; c++) {
                if (currentLevel[r][c] != null) {
                    gfx.setPaint(Color.BLACK);
                    gfx.fill(currentLevel[r][c].getRect());
                } else {
                    gfx.setPaint(Color.GRAY);
                    gfx.fill(Wall.createRect(r, c));
                }
            }
        }
        gfx.setPaint(Color.orange);
        gfx.fill(explorer.getRect());
        gfx.setPaint(Color.YELLOW);
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gfx.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        gfx.drawString("Level " + currentLevelID, (int) 605, (int) 40);
        gfx.drawString(moves + " moves", (int) 605, (int) 60);
        gfx.drawString("[M] Menu", (int) 605, (int) 80);
    }

    public void redraw3D()
    {
        Graphics2D gfx = (Graphics2D) window().getGraphics();
        Location exp = explorer.loc();
        int direction = explorer.d();
        int distance_to_wall = 0;
        try {
            while (exp.isInRange(0, 0, 14, 14) && currentLevel[exp.r()][exp.c()] == null) {
                exp = exp.moveIn(direction);
                distance_to_wall++;
            }
        } catch (IndexOutOfBoundsException e) { }

        gfx.setPaint(Color.white);
        gfx.fillRect(0, 0, 720, 720);
        System.out.println(window().getHeight());

        for (int i=distance_to_wall;i>0;i--)
        {
            int size = 720/distance_to_wall;
            int indent = i * size;
            int j =  i + distance_to_wall;
            
            Rectangle r = new Rectangle(360 - indent/2, 360 - indent/2, indent, indent);
            
            gfx.setPaint(Color3.toAWT(new Color3(j/40f, j/40f - .2f, j/40f - .2f)));
            gfx.fill(r);
            gfx.setPaint(Color.yellow);
            gfx.draw(r);
            
            Location currentSquare = explorer.loc().moveIn(explorer.d(), i);
            System.out.println(currentSquare);
            if (!isWallAt(currentSquare.moveIn(2)))
            {
                // draw right hole
            }
            else if (!isWallAt(currentSquare.moveIn(4)))
            {
                // draw left hole
                Polygon polyTop = new Polygon();
                polyTop.addPoint(360 - indent, 360 - indent/2);
                polyTop.addPoint(360 - indent, 360 - indent);
                polyTop.addPoint(360 - indent/2, 360 - indent/2);
    
                Polygon polyBottom = new Polygon();
                polyBottom.addPoint(360 - indent, 360 + indent/2);
                polyBottom.addPoint(360 - indent, 360 + indent);
                polyBottom.addPoint(360 - indent/2, 360 + indent/2);

                gfx.setColor(Color3.toAWT(new Color3(.25,.15, .15)));
                gfx.fill(polyTop);
                gfx.fill(polyBottom);

                gfx.fill(new Rectangle(360 - indent, 360 - indent/2, indent/2, indent));
                gfx.setColor(Color.yellow);

                gfx.draw(polyTop);
                gfx.draw(polyBottom);
            }
        }

        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gfx.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        gfx.drawString("Level " + currentLevelID, (int) 605, (int) 40);
        gfx.drawString(moves + " moves", (int) 605, (int) 60);
        gfx.drawString("[M] Menu", (int) 605, (int) 80);
        System.out.println(distance_to_wall);
    }

    public boolean isWallAt(Location loc)
    {
        if (loc.r() > 14 || loc.r() < 0)
            return false;
        if (loc.c() > 14 || loc.c() < 0)
            return false;
        return currentLevel[loc.r()][loc.c()] != null;
    }

    public static void main(String[] args) {
        launch(args, MazeGame.class);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    public void menu()
    {
        disabled = !disabled;

        // TODO implementation
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 77)
        {
            menu();
        }
        if (disabled) return;
        switch (key)
        {
            case 32:
            {
                is3d = !is3d;
                break;
            }
            case 37:
            {
                explorer.Turn(-1);
                moves++;
                break;
            }
            case 38:
            {
                Location fwd = explorer.Move(1);
                if (explorer.test(fwd, currentLevel))
                {
                    explorer.confirm(fwd);
                    moves++;
                }
                break;
            }
            case 39:
            {
                explorer.Turn(1);
                moves++;
                break;
            }
            case 40:
            {
                Location fwd = explorer.Move(-1);
                if (explorer.test(fwd, currentLevel))
                {
                    explorer.confirm(fwd);
                    moves++;
                }
                break;
            }
        }
        redraw();
        if (explorer.loc().equals(end))
        {
            currentLevelID++;
            unlockedLevelID++;
            moves = 0;
            if (currentLevelID > lastLevelID)
            {
                disabled = true;
                currentLevelID = lastLevelID;
                unlockedLevelID = lastLevelID;
                win();
                return;
            }
            read_to_walls(currentLevelID);
            redraw();
        }
    }

    public void redraw()
    {
        if (is3d) redraw3D(); else redraw2D();
    }

    public void win()
    {
        Window w = window();
        Graphics2D gfx = (Graphics2D) w.getGraphics();
        gfx.setFont(new Font("TimesRoman", Font.BOLD, 60));
        gfx.setPaint(Color.YELLOW);
        gfx.drawString("YOU WIN!", 100, 100);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}