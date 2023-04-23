package Graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import Config.GameConfig;
import Time.Timers;

public class Animation {

	private Sprite sprite; 
    private final int count; 
    private final int cols; 
    private int i = 0; 
    private int dx = 0; // toa do bat dau chay cua i 
    
     private float calcTime;

    private float totalTime;

    private int timePerFrame;
   
    private boolean done = false;
    private boolean loop;
 
    private Timers timers;
    
    public Animation(String path , int count, int colums,  int totalTime , int dx) {
    	sprite = new Sprite(path); 
        this.count = count;
        this.cols = colums; 
        this.totalTime = totalTime;
        this.dx = dx; 
        timers = Timers.getInstance();
        timePerFrame = totalTime / count;
        reset();
    }

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
        	
            g.drawImage(sprite.getSprite(dx + i, cols), dX, dY, null); 
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

            i = (index % count) ; 
            //System.out.println(timers.getDeltaTime());
            //System.out.println(i);

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
