package site.alex_xu.games.flappybird;

import site.alex_xu.dev.frameworks.awaengine.controls.Keyboard;
import site.alex_xu.dev.frameworks.awaengine.core.MasterLoader;
import site.alex_xu.dev.frameworks.awaengine.graphics.Texture;
import site.alex_xu.dev.frameworks.awaengine.scene.Scene;
import site.alex_xu.dev.utils.Clock;
import site.alex_xu.dev.utils.geometry.Rect;

import java.util.ArrayList;

public class World extends Scene {
    public float groundX = 0;
    public boolean gameRunning = false;
    public boolean gameStarted = false;
    public boolean gameEnded = false;
    public int score = 0;
    ArrayList<PipeGroup> pipeGroups = new ArrayList<>();
    Clock pipeClock = new Clock();
    Texture texBackground;
    Texture texGround;
    Bird bird;
    Keyboard keyboard = new Keyboard() {
        @Override
        public void onPress(int key, boolean isRepeat, char character) {
            if (key == Key.KEY_SPACE) {
                if (!gameStarted) {
                    pipeClock.reset();
                }
                gameStarted = true;
                bird.flap();
            }
        }
    };

    @Override
    public void setup() {
        texBackground = MasterLoader.resources.getTexture("textures/background/day.png");
        texGround = MasterLoader.resources.getTexture("textures/ground.png");

        bird = new Bird(this);
        getRootNode().attachChild(bird);
        keyboard.createListener();
        pipeGroups.add(new PipeGroup());

    }

    @Override
    public void destroy() {
        keyboard.destroyListener();
    }

    @Override
    public void update() {
        getCamera().position.set(getWindow().getWidth() / 2f, getWindow().getHeight() / 2f);
        if (gameRunning) {
            groundX -= Time.delta * 90;
            if (groundX < -24) {
                groundX = 0;
            }
            Rect birdRect = bird.getRect();
            for (PipeGroup pipeGroup : pipeGroups) {
                pipeGroup.update();
                if (pipeGroup.getUpRect().intersectsWith(birdRect) || pipeGroup.getDownRect().intersectsWith(birdRect)) {
                    gameEnded = true;
                }
                if (pipeGroup.getUpRect().getRight() < birdRect.getLeft()) {
                    if (!pipeGroup.scored) {
                        pipeGroup.scored = true;
                        score++;
                    }
                }
            }

            if (pipeClock.getElapsedTime() > 4) {
                pipeClock.reset();
                pipeGroups.add(new PipeGroup());
                if (pipeGroups.size() > 5) {
                    pipeGroups.remove(0);
                }
            }

        }


        gameRunning = gameStarted && !gameEnded;

        if (bird.getRect().getBottom() > texBackground.getHeight()) {
            gameEnded = true;
            bird.velY = 0;
            bird.position.y = texBackground.getHeight() - bird.origin.y;
        }

        if (bird.getRect().getTop() < 0) {
            gameEnded = true;
        }

    }

    @Override
    public void draw() {
        blit(texBackground);

        for (PipeGroup pipeGroup : pipeGroups) {
            blit(pipeGroup);
        }

        blit(groundX, texBackground.getHeight(), texGround);

        stroke(0, 0);
        fill(247, 228, 126);
        rect(
                0, texBackground.getHeight() + texGround.getHeight(),
                getWindow().getWidth(), 30
        );


    }

    @Override
    public void drawUI() {
        fill(255);
        textFont(MasterLoader.resources.getFont("fonts/04B_19__.TTF"));
        String content = "Score: " + score;
        text(
                getWindow().getWidth() / 2f - getTextWidth(content) / 2f,
                10,
                content
        );
    }
}
