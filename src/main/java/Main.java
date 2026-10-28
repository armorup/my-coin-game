import zero.Key;
import zero.Color;
import zero.GameApp;
import zero.ui.Button;
import zero.ui.Label;
import zero.ui.Row;

/** Main reads input, then explicitly updates and draws its Player. */
public class Main extends GameApp {
    private Player player;
    private Coin coin;
    private int points;
    private Label scoreLabel;
    private Label message;

    @Override
    public void settings() {
        padding(20);
        size(640, 400);
    }

    @Override
    public void setup() {
        player = new Player(40, 180);
        coin = new Coin(220, 180);
        scoreLabel = new Label("");
        message = new Label("");
        var restartButton = new Button("Restart");
        restartButton.onClick(() -> restart());
        controls(new Row(12, scoreLabel, message, restartButton));
        restart();
    }

    public void restart() {
        player = new Player(40, 180);
        coin = new Coin(220, 180);
        points = 0;
        scoreLabel.setText("Score: 0");
        message.setText("Collect the gold square.");
        showGame();
    }

    @Override
    public void update(double seconds) {
        int horizontal = 0;
        int vertical = 0;
        if (keyDown(Key.LEFT))
            horizontal--;
        if (keyDown(Key.RIGHT))
            horizontal++;
        if (keyDown(Key.UP))
            vertical--;
        if (keyDown(Key.DOWN))
            vertical++;
        player.update(horizontal, vertical, seconds, width(), height());
        if (!coin.isCollected() && coin.touches(player)) {
            coin.collect();
            points++;
            scoreLabel.setText("Score: " + points);
        }
    }

    @Override
    public void draw() {
        background(Color.rgb(240, 248, 255));
        fill(Color.rgb(47, 79, 79));
        textSize(20);
        text("Click the canvas, then hold the arrow keys.", 20, 30);
        fill(Color.rgb(47, 79, 79));
        coin.draw(this);
        player.draw(this);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
