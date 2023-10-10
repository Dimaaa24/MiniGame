package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenx;
    public final int screeny;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screeny=gp.screenHeight/2-gp.TileSize-gp.TileSize/2;
        screenx=gp.screenWidth/2-gp.TileSize+gp.TileSize/2;
        setDefault();
        getPlayerModel();
        solid=new Rectangle(29,55,53,44);
    }

    public void setDefault() {
        worldx = gp.TileSize*15;
        worldy = gp.TileSize*15;
        speed = 5;
        direction = "idle";
    }

    public void getPlayerModel() {
        String FileSep="/main_char/";
        try {
            idle = ImageIO.read(getClass().getResourceAsStream(FileSep+"idle.png"));
            up = ImageIO.read(getClass().getResourceAsStream(FileSep+ "up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream(FileSep+"up2.png"));
            down = ImageIO.read(getClass().getResourceAsStream(FileSep+"down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream(FileSep+ "down2.png"));
            left = ImageIO.read(getClass().getResourceAsStream(FileSep+"left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream(FileSep+"left2.png"));
            right = ImageIO.read(getClass().getResourceAsStream(FileSep+"right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream(FileSep+ "right2.png"));
            se = ImageIO.read(getClass().getResourceAsStream(FileSep+"se1.png"));
            se2 = ImageIO.read(getClass().getResourceAsStream(FileSep+ "se2.png"));
            sv = ImageIO.read(getClass().getResourceAsStream(FileSep+"sv1.png"));
            sv2 = ImageIO.read(getClass().getResourceAsStream(FileSep+"sv2.png"));
            nv = ImageIO.read(getClass().getResourceAsStream(FileSep+"nv1.png"));
            nv2 = ImageIO.read(getClass().getResourceAsStream(FileSep+"nv2.png"));
            ne = ImageIO.read(getClass().getResourceAsStream(FileSep+"ne1.png"));
            ne2 = ImageIO.read(getClass().getResourceAsStream(FileSep+"ne2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {
        if (keyH.down) {
            direction = "down";
            if (keyH.right) {
                direction = "se";
            }
            if (keyH.left) {
                direction = "sv";
            }
        } else if (keyH.up) {
            direction = "up";
            if (keyH.right) {
                direction = "ne";
            }
            if (keyH.left) {
                direction = "nv";
            }
        } else if (keyH.left) {
            direction = "left";
        } else if (keyH.right) {
            direction = "right";
        }
        else
            direction="idle";

        //creates collison and checks
        collides=false;
        gp.checker.checkCollision(this);

        if(!collides)
        {
            switch(direction)
            {
                case "up":
                    worldy -= speed;
                    break;
                case "down":
                    worldy += speed;
                    break;
                case "left":
                    worldx -= speed;
                    break;
                case "right":
                    worldx += speed;
                    break;
                case "ne":
                    worldy -= speed;
                    worldx += speed;
                    break;
                case "nv":
                    worldy -= speed;
                    worldx -= speed;
                    break;
                case "se":
                    worldx += speed;
                    worldy += speed;
                    break;
                case "sv":
                    worldy += speed;
                    worldx -= speed;
                    break;
            }
        }
        spriteCounter++;
        if(spriteCounter>20)
        {
            if(spriteNum==1)
                spriteNum=2;
            else 
                spriteNum=1;
            spriteCounter=0;
        }
    }

    public void draw(Graphics g2) {
        BufferedImage image=null;
        switch(direction)
        {
            case "idle":
                image=idle;
                break;
            case "down":
                if(spriteNum==1)
                image=down;
                else 
                    image=down2;
                break;
            case "up":
                if(spriteNum==1)
                image=up;
                else 
                    image=up2;
                break;
            case "left":
                if(spriteNum==1)
                image=left;
                else 
                    image=left2;
            break;
            case "right":
                if(spriteNum==1)
                image=right;
                else 
                    image=right2;
            break;
            case "nv":
                if(spriteNum==1)
                image=nv;
                else 
                    image=nv2;
            break;
            case "ne":
                if(spriteNum==1)
                image=ne;
                else 
                    image=ne2;
            break;
            case "sv":
                if(spriteNum==1)
                image=sv;
                else 
                    image=sv2;
            break;
            case "se":
                if(spriteNum==1)
                image=se;
                else 
                    image=se2;
            break;
        }
        g2.drawImage(image,screenx,screeny,gp.TileSize,gp.TileSize,null);
    }

}
