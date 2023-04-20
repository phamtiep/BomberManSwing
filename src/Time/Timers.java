package Time;



public class Timers {
    private static Timers instance = null;
    private long ticks;
    private double deltaTime;

    public static Timers getInstance() {
        if (instance == null) {
            instance = new Timers();
        }
        return instance;
    }

    private Timers() {
        deltaTime = 0.0;
        ticks = 0;
    }

    public void update(long now) {

        deltaTime = now - ticks;
        ticks = now;
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public long getTicks() {
        return ticks;
    }
}
