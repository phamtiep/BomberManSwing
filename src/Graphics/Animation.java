package Graphics;

import java.awt.Graphics;
import java.awt.Image;

import Config.GameConfig;
import Time.Timers;

public class Animation {
    private final Image image;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private int sX;
    private int sY;
    private final int sWidth;
    private final int sHeight;
    private final int dWidth;
    private final int dHeight;
 

    
    private float calcTime;
    //entire animation
    private float totalTime;
    //Stores how long each animation frame should be on screen
    private int timePerFrame;
    //Is set to true only if the wrap mode is once and the last frame of the animation is reached
    private boolean done;
    private boolean loop;
    // Get Delta Time between each frame
    private Timers timers;

    /**
     *
     * @param image : spriteSheet.
     * @param count : the number of frames.
     * @param columns : the number of the columns.
     * @param totalTime : (milli) total amount of time for the entire animation.
     * @param offsetX : the source rectangle's X coordinate position.
     * @param offsetY : the source rectangle's Y coordinate position.
     * @param sWidth : the source rectangle's width.
     * @param sHeight : the source rectangle's height.
     */
    public Animation(Image image, int count, int columns, int totalTime,
                     int offsetX, int offsetY, int sWidth, int sHeight) {
        this.image = image;
        this.count = count;
        this.columns = columns;
        this.totalTime = totalTime;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.sWidth = sWidth;
        this.sHeight = sHeight;

        
        this.dWidth =GameConfig.SIZE_BLOCK;
        this.dHeight = GameConfig.SIZE_BLOCK;
        

        timers = Timers.getInstance();
        timePerFrame = totalTime / count;
        reset();
    }

    
    public void reset() {
        sX = offsetX;
        sY = offsetY;
        done = false;
        calcTime = 0.0f;
        loop = false;
    }

    /**
     *
     * @param gc : graphics
     * @param dX : the destination rectangle's X coordinate position.
     * @param dY : the destination rectangle's Y coordinate position
     */
    public void render(Graphics g, int dX, int dY) {
        if (!done) {
            g.drawImage(image, sX, sY, sWidth, sHeight, dX, dY,
                    dWidth, dHeight,null);
        }
    }

    public void update() {
        if (!done) {
            calcTime += timers.getDeltaTime();
            if (calcTime >= totalTime) {
                if (loop) {
                    calcTime = 0.0f;
                } else {
                    done = true;
                    // stop at last animation then say that the animation is done
                    calcTime = totalTime - timePerFrame;
                }
            }
            int index = (int) (calcTime / timePerFrame);
            sX = (index % columns) * (sWidth )  + offsetX;
            sY = ((int) (index / columns)) * sHeight + offsetY;
        }
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public float getCalcTime() {
        return calcTime;
    }
}
