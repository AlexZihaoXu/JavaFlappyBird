package site.alex_xu.games.flappybird;

import site.alex_xu.dev.frameworks.awaengine.core.Settings;
import site.alex_xu.dev.frameworks.awaengine.video.Window;

public class FlappyBird extends Window {

    FlappyBird() {
        super("FlappyBird", 552, 644 + 28 + 30);
        Settings.Video.reduceFpsWhenLostFocus = false;
    }

    public static void main(String[] args) {
        new FlappyBird().launch();
    }

    @Override
    public void setup() {
        setScene(new World());
        setResizable(false);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void draw() {
        clear(0);
    }

    @Override
    public void update() {

    }
}
