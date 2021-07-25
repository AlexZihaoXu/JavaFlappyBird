package site.alex_xu.games.flappybird;

import site.alex_xu.dev.frameworks.awaengine.core.MasterLoader;
import site.alex_xu.dev.frameworks.awaengine.graphics.Texture;
import site.alex_xu.dev.frameworks.awaengine.scene.Node;

public class Pipe extends Node {

    public Texture texture;

    public Pipe() {
        texture = MasterLoader.resources.getTexture("textures/pipe.png");
        origin.set(0, texture.getHeight() / 2f - 360);
    }

    @Override
    public void draw() {
        blit(texture);
    }
}
