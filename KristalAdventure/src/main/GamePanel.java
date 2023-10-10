package main;

import entity.Player;
import tiles.Tile;
import tiles.TileSet;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //screen settings
    final int ogTileSize = 24;  //24x24 pixels tile settings
    final int scale = 4; //4x times scaling
    public final int TileSize = scale * ogTileSize; //24*4pixels tiles

    //generating the panel sizes for a 1920x1080 screen
    public final int maxWidthTiles = 15; //30*72 tiles=1024
    public final int maxHeightTile = 11; //16*48 tiles=768
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int screenWidth = (int)screenSize.getWidth();
    public int screenHeight = (int)screenSize.getHeight();

    //world settings
    public final int maxCol=30;
    public final int maxRow=30;
    public final int worldWidth=maxCol*TileSize;
    public final int worldHeight=maxRow*TileSize;

    //creating the thread for the game+key handler
    Thread gameThread;
    int FPS = 60; //number of frames we want per second
    KeyHandler keyH = new KeyHandler();

    //initiating player entity+collison
    public Player player=new Player(this,keyH);
    public CollisionCheck  checker=new CollisionCheck(this);

    //initiating tileset
    TileSet tileset=new TileSet(this);

    GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double draw_interval = 1000000000 / FPS;//we use nanoTime so we divinde a second(1000000000 nanoseconds)by FPS=>0.016seconds
        double delta=0 ; //set delta to 0
        long last_time = System.nanoTime(); //get current time of the thread
        long current_time;//initialize the current time of each frame

        while (gameThread != null) {
            current_time = System.nanoTime();
            //get the time
            delta += (current_time - last_time) / draw_interval;
            //calculate a difference between the current time and last time update
            //divide it by 0.016 and when 1 interval has passed delta will have accumulated 1
            last_time = current_time; //update the time
            if (delta >= 1) { //check is 0.016 seconds have passed
                update();
                repaint();
                delta--;
                //repaint and reset timer
            }
        }
    }

    //drawing character&other stuff
    public void paintComponent(Graphics g) {
        //creating the graphics components for 2D
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileset.draw(g2);//tiles
        player.draw(g2);//readraw character
        g2.dispose();
    }

    //updating game details
    public void update() {
        player.update();
    }
}
