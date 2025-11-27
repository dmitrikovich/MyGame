package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

import java.awt.*;

public class Bullet extends AbstractActor implements Fireable, Movable {
    Animation bulletAnimation;
    public Bullet() {
        bulletAnimation = new Animation("sprites/bullet.png", 16, 16);
        setAnimation(bulletAnimation);
    }


    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public void startedMoving(Direction direction) {
        bulletAnimation.setRotation(direction.getAngle());
    }
    @Override
    public void stoppedMoving() {
    }

    @Override
    public void collidedWithWall() {
         if(getScene() == null) return;
         getScene().removeActor(this);
    }

    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        new Loop<>(
            new Invoke<>(() -> {
                assert getScene() != null;
                for(Actor actor : getScene().getActors()) {
                    if(actor instanceof Alive && !(actor instanceof Ripley)) {
                        if(this.intersects(actor)) {
                            ((Alive) actor).getHealth().drain(15);
                            scene.removeActor(this);
                        }
                    }
                }
            })
        ).scheduleFor(this);
    }
}
