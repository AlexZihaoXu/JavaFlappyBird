package site.alex_xu.games.flappybird;

import site.alex_xu.dev.frameworks.awaengine.core.MasterLoader;
import site.alex_xu.dev.frameworks.awaengine.graphics.Texture;
import site.alex_xu.dev.frameworks.awaengine.scene.Node;
import site.alex_xu.dev.utils.geometry.Rect;

public class Bird extends Node {
    Texture[] textures;
    float texIndex = 0;
    float velY = -500;
    World world;

    public Bird(World world) {
        this.world = world;
        textures = new Texture[]{
                MasterLoader.resources.getTexture("textures/bird/1-1.png"),
                MasterLoader.resources.getTexture("textures/bird/1-2.png"),
                MasterLoader.resources.getTexture("textures/bird/1-3.png"),
                MasterLoader.resources.getTexture("textures/bird/1-2.png"),
        };
        origin.set(getCurrentTexture().getWidth() / 2f, getCurrentTexture().getHeight() / 2f);
        position.set(100, getWindow().getHeight() / 2f);
    }

    public void flap() {
        if (world.gameStarted && !world.gameEnded) {
            velY = -630;
        }
    }

    public Rect getRect() {
        return new Rect(
                position.x - origin.x, position.y - origin.y,
                getCurrentTexture().getWidth(),
                getCurrentTexture().getHeight()
        );
    }

    public Texture getCurrentTexture() {
        return textures[(int) texIndex];
    }

    @Override
    public void draw() {
        if (world.gameStarted && !world.gameEnded)
            rotation = Math.min(Math.max(velY * 0.05f, -30), 30);
        else
            rotation += (0 - rotation) * Video.delta * 10;
        blit(getCurrentTexture());

        texIndex += Video.delta * 10;
        if ((int) texIndex >= textures.length) {
            texIndex = 0;
        }
    }

    @Override
    public void update() {
        if (world.gameStarted) {
            velY += Time.delta * 2000;
            position.move(0, velY * Time.delta);
        }
    }
}
