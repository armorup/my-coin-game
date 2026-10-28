import zero.Color;
import zero.GameApp;

public class Player {
    private double x;
    private double y;
    private final double size = 24;
    private final double speed = 180;

    public Player(double startX, double startY) {
        x = startX;
        y = startY;
    }

    public void update(int horizontal, int vertical, double seconds,
                       double canvasWidth, double canvasHeight) {
        x += horizontal * speed * seconds;
        y += vertical * speed * seconds;
        x = Math.max(0, Math.min(x, canvasWidth - size));
        y = Math.max(0, Math.min(y, canvasHeight - size));
    }

    public void draw(GameApp app) {
        app.fill(Color.rgb(100, 149, 237));
        app.rect(x, y, size, size);
    }
}