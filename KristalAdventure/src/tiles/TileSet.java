package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class TileSet {
    public int timer;
    GamePanel gp;
    public Tile[] tiles;
    public int[][] MapTilesNum;

    public TileSet(GamePanel gp) {
        timer = 0;
        this.gp = gp;
        tiles = new Tile[70];
        MapTilesNum = new int[gp.maxCol][gp.maxRow];
        loadMap("map1");
        getTileImage();
        getTileCollision();
    }

    public void getTileImage() {
        String path = "/tiles/";
        for (int i = 0; i<=68 ; i++) {
            try {
                tiles[i] = new Tile();
                tiles[i].solidarea=new Rectangle(0,0,96,96);
                tiles[i].model = ImageIO.read(getClass().getResourceAsStream(path + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        tiles[47].solidarea=new Rectangle(9,12,87,84);
    }

    public void getTileCollision()
    {
       String path="/maps/colision.txt";
       int[] collisions=new int[68];
       int i=0;
       InputStream input=getClass().getResourceAsStream(path);
       BufferedReader reader=new BufferedReader(new InputStreamReader(input));
        try {
            String line=reader.readLine();
            String[] numbers = line.split(",");
            while(numbers.length>i) {
                int nr = Integer.parseInt(numbers[i]);
                collisions[i] = nr;
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int j=0;j<i;j++)
        {
            tiles[collisions[j]].collision=true;
        }
    }

    public void loadMap(String file) {
        String folder="/maps/"+file+".csv";
        try {
            InputStream is = getClass().getResourceAsStream(folder);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxCol && row < gp.maxRow) {
                String line = br.readLine();
                while (col < gp.maxCol) {
                    String[] numbers = line.split(",");
                    int nr = Integer.parseInt(numbers[col]);
                    MapTilesNum[col][row] = nr;
                    col++;
                }
                if (col == gp.maxCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics g2) {

        int worldcol = 0;
        int worldrow = 0;

        while(worldcol < gp.maxCol && worldrow < gp.maxRow)
        {
            int nr=MapTilesNum[worldcol][worldrow];
            int worldx=worldcol*gp.TileSize;
            int worldy=worldrow*gp.TileSize;
            int screenx=worldx-gp.player.worldx+gp.player.screenx;
            int screeny=worldy-gp.player.worldy+gp.player.screeny;
            if(worldx-gp.TileSize<gp.player.worldx+gp.player.screenx &&
               worldx+gp.TileSize>gp.player.worldx-gp.player.screenx &&
               worldy-2*gp.TileSize<gp.player.worldy+gp.player.screeny &&
               worldy+gp.TileSize>gp.player.worldy-gp.player.screeny)
            {
                g2.drawImage(tiles[nr].model, screenx, screeny, gp.TileSize, gp.TileSize, null);
            }
            worldcol++;
            if(worldcol==gp.maxCol)
            {
                worldcol=0;
                worldrow++;
            }
        }
    }
}
