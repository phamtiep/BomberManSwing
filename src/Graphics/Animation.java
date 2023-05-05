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
    private int row = 0; // toa do bat dau chay cua i 
    
     private int calcTime;

    private int totalTime;

    private int timePerFrame;
    
    private boolean done = false;
    private boolean loop;
    private int speedIncrementAnimationSpeed = 1;
    private Timers timers;
    
    public Animation(String path , int count, int colums,  int totalTime , int row) {
    	  sprite = new Sprite(path,count); 
        this.count = count;
        this.cols = colums; 
        this.totalTime = totalTime;
        this.row = row; 
        timers = Timers.getInstance();
        timePerFrame = totalTime / count;
        this.speedIncrementAnimationSpeed = 1;
        reset();
    }
    public Animation(String path , int count, int colums,  int totalTime , int row,int speedIncrementAnimationSpeed) {
        sprite = new Sprite(path,count); 
        this.count = count;
        this.cols = colums; 
        this.totalTime = totalTime;
        this.row = row; 
        timers = Timers.getInstance();
        timePerFrame = totalTime / count;
        this.speedIncrementAnimationSpeed = speedIncrementAnimationSpeed;
        reset();
    }
    public Animation(String path , int count, int colums,  int totalTime , int row,int width , int height) {
          sprite = new Sprite(path,width,height,count); 
          this.count = count;
          this.cols = colums; 
          this.totalTime = totalTime;
          this.row = row; 
          timers = Timers.getInstance();
          timePerFrame = totalTime / count;
          this.speedIncrementAnimationSpeed = 1;
          reset();
      }
    
    public Animation(String path , int count, int colums,  int totalTime , int row,int width , int height,int speedIncrementAnimationSpeed) {
        sprite = new Sprite(path,width,height,count); 
        this.count = count;
        this.cols = colums; 
        this.totalTime = totalTime;
        this.row = row; 
        timers = Timers.getInstance();
        timePerFrame = totalTime / count;
        this.speedIncrementAnimationSpeed = 2;
        reset();
    }

    public void reset() {
        done = false;
        calcTime = 0;
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
        	
            g.drawImage(sprite.getSprite(row + i, cols), dX, dY, null); 
        }
    }

    public void update() {
        if (!done) {
            calcTime += timers.getDeltaTime();
            if (calcTime >= totalTime) {
                if (loop) {
                    calcTime = 0;
                    timePerFrame /= this.speedIncrementAnimationSpeed;
                } else {
                    done = true;
                    // stop at last animation then say that the animation is done
                    calcTime = totalTime - timePerFrame;
                }
            }
            int index = 1;
            try {
             index = (int) (calcTime / timePerFrame);
            }
            catch (Exception e) {
               System.out.println(e.toString());
            }
            i = (index % count) ; 
            if(speedIncrementAnimationSpeed!=1)
            System.out.println(timePerFrame);
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

    
    public int getTotalTime() {
        return totalTime;
    }
    
    public float getCalcTime() {
        return calcTime;
    }
}
