package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

import static sk.tuke.kpi.gamelib.graphics.Animation.PlayMode.LOOP_PINGPONG;

public class Ripley extends AbstractActor implements Movable {
    private int speed;
    private Animation animation;

    public Ripley() {
        super("Ellen");
        speed = 2;
        animation = new Animation("sprites/player.png", 32, 32, 0.1f, LOOP_PINGPONG);
        setAnimation(animation);
//        getAnimation().stop();
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        animation.setRotation(direction.getAngle());
        setAnimation(animation);
    }

    @Override
    public void stoppedMoving() {
        this.getAnimation().stop();
    }
}
