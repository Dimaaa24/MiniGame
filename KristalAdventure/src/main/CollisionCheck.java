package main;
import entity.Entity;

import java.awt.*;

public class CollisionCheck
{
    GamePanel gp;
    public CollisionCheck(GamePanel gp)
    {
        this.gp=gp;
    }

    public void checkCollision(Entity entity)
    {
        int solid_top_left=entity.worldx+entity.solid.x;
        int solid_top_right=entity.worldx+entity.solid.x+entity.solid.width;
        int solid_bottom_left=entity.worldy+entity.solid.y;
        int solid_bottom_right=entity.worldy+entity.solid.y+entity.solid.height;

        int  left=solid_top_left/gp.TileSize;
        int top=solid_top_right/gp.TileSize;
        int bottom=solid_bottom_left/gp.TileSize;
        int right=solid_bottom_right/gp.TileSize;

        Rectangle Tile1,Tile2;

    }
    public boolean collides(Rectangle a,Rectangle b)
    {
        return a.x + a.width < b.x && a.y + a.height < b.y;
    }
}
