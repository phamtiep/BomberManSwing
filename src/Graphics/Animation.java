package Graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import Config.GameConfig;
import Time.Timers;

public class Animation {
<<<<<<< HEAD
	private Sprite sprite; 
    private final int count; 
    private final int cols; 
    private int i = 0; 
    //Used to keep track of how long current animation frame has been on screen
=======
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
 

    
>>>>>>> 74f135354b918cf340879eb0c5adf88fd91b6417
    private float calcTime;
    //entire animation
    private float totalTime;
    //Stores how long each animation frame should be on screen
    private int timePerFrame;
    //Is set to true only if the wrap mode is once and the last frame of the animation is reached
    private boolean done = false;
    private boolean loop;
    // Get Delta Time between each frame
    private Timers timers;
    
    public Animation(String path , int count, int colums,  int totalTime) {
    	sprite = new Sprite(path); 
        this.count = count;
        this.cols = colums; 
        this.totalTime = totalTime;
<<<<<<< HEAD
       
=======
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.sWidth = sWidth;
        this.sHeight = sHeight;

        
        this.dWidth =GameConfig.SIZE_BLOCK;
        this.dHeight = GameConfig.SIZE_BLOCK;
        

>>>>>>> 74f135354b918cf340879eb0c5adf88fd91b6417
        timers = Timers.getInstance();
        timePerFrame = totalTime / count;
        reset();
    }

<<<<<<< HEAD
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
     * @param dWidth: the destination rectangle's width.
     * @param dHeight: the destination rectangle's height.
     */
=======
    
>>>>>>> 74f135354b918cf340879eb0c5adf88fd91b6417
    public void reset() {
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
        	
            g.drawImage(sprite.getSprite(i, cols), dX, dY, null); 
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
<<<<<<< HEAD
            i = (index % count)*GameConfig.SIZE_BLOCK ; 
            //System.out.println(i);
=======
            sX = (index % columns) * (sWidth )  + offsetX;
            sY = ((int) (index / columns)) * sHeight + offsetY;
>>>>>>> 74f135354b918cf340879eb0c5adf88fd91b6417
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
