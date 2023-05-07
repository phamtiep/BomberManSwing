package Bomb;

import java.awt.Graphics;
import java.awt.Image;

import Enitity.Entity;
import Graphics.SpriteSheet;
import Time.Timers;

public class Flame extends Entity {
    public static final int MAX_LENGTH = 6;

    public Image img = null;
    public static final int dispTime = 30_000_0000;
    private boolean isDone = false;
    /**
     * @return the isDone
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * @param isDone the isDone to set
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    private int time = 0;
    public enum FlameDirection {
        UP, DOWN, LEFT, RIGHT, CENTER
    }

    public boolean isLast;
    public static SpriteSheet inp = new SpriteSheet("/anhgame.png");

    public Flame(int x, int y, FlameDirection direction, boolean isLast) {
        this.x = x;
        this.y = y;
        switch (direction) {
        case UP:
            if (isLast)
                img = inp.getSprite(1, 5);
            else {
                img = inp.getSprite(1, 6);
            }
            break;
        case CENTER: {
            img = inp.getSprite(0, 4);
        }
            break;
        case LEFT:
            if (isLast)
                img = inp.getSprite(3, 4);
            else {
                img = inp.getSprite(4, 4);
            }
            break;
        case DOWN:
            if (isLast)
                img = inp.getSprite(0, 6);
            else {
                img = inp.getSprite(0, 5);
            }
            break;
        case RIGHT:
            if (isLast)
                img = inp.getSprite(2, 4);
            else {
                img = inp.getSprite(1, 4);
            }
            break;
        default:
            break;
        }
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        if(!isDone) {
        g.drawImage(img, x, y, null);
        }
    }

    @Override
    public void update() {
        time += Timers.getInstance().getDeltaTime();
        if(time >= dispTime) isDone = true;
    }

}