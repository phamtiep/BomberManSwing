package Main;

import java.awt.BorderLayout;

import javax.swing.JFrame;


import StateManager.StateManager;
import StateManager.StateManager.State;
import Time.Timers;

public class Main extends JFrame implements  Runnable {
   
    public static final int SCREEN_WIDTH = 32*20;
    public static final int SCREEN_HEIGHT = 32*15;
    private static Main instance = null;
    
    
    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }
    private Main() {
       
    }

    public void startGame() {
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        StateManager.getInstance().setCurrentState(State.GAME);
        this.setVisible(true);
        Thread gameThread = new Thread(this);
        gameThread.start();
        
    }
    @Override
    public void run() {
      long FPS = 120;

      long previousTime = System.nanoTime();
      long currentTime;
      long sleepTime;

      long period = (long) 1e9 / FPS;

      while (true) {
        // gameState.Render()
        Timers.getInstance().update(System.nanoTime());
       
        repaint();
        currentTime = System.nanoTime();
        sleepTime = period - (currentTime - previousTime);
        try {

          if (sleepTime > 0)
            Thread.sleep(sleepTime / 1000000);
          else
            Thread.sleep(period / 2000000);

        } catch (Exception e) {
        }

        previousTime = System.nanoTime();

        // isRunning = false;
      }
    }
    public static void main(String args[]) {
        
        Main.getInstance().startGame();
    }
}
