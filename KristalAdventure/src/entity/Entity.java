package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldx,worldy;
    public int speed;

    //images for the entity animation
    public BufferedImage idle,tile;
    public BufferedImage up,down,left,right,se,sv,ne,nv;
    public BufferedImage up2,down2,left2,right2,se2,sv2,ne2,nv2;
    public String direction;

    //
    public Rectangle solid;
    public boolean collides=false;

    //animating the walk
    public int spriteCounter=0;
    public int spriteNum=1;
}
