package site.alex_xu.games.flappybird;

import site.alex_xu.dev.frameworks.awaengine.scene.Node;
import site.alex_xu.dev.utils.FastMath;
import site.alex_xu.dev.utils.geometry.Rect;

public class PipeGroup extends Node {
    public Pipe up;
    public Pipe down;
    public boolean scored = false;

    public PipeGroup() {
        up = new Pipe();
        down = new Pipe();

        up.scale.set(1, -1);

        attachChild(up);
        attachChild(down);

        position.set(getWindow().getWidth(), getWindow().getHeight() / 2f);
        position.move(0, FastMath.randRange(-2, 2) * 100);
    }

    public Rect getUpRect() {
        return new Rect(
                position.x, position.y + 125,
                up.texture.getWidth(),
                up.texture.getHeight()
        );
    }

    public Rect getDownRect() {
        return new Rect(
                position.x, position.y - up.texture.getHeight() - 125,
                up.texture.getWidth(),
                up.texture.getHeight()
        );
    }

    @Override
    public void update() {
        position.move(-Time.delta * 90, 0);
    }
}
