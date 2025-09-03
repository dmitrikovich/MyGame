package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move<A extends Movable> implements Action<A> {
    private Direction direction;
    private float duration;
    private A actor;
    private boolean isDone;

    private float movingTime;


    public Move(Direction direction, float duration) {
        this.direction = direction;
        this.duration = duration;
        isDone = false;
    }

    public Move(Direction direction) {
        this.direction = direction;
        this.duration = 0;
        isDone = false;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public @Nullable A getActor() {
        return actor;
    }

    @Override
    public void setActor(@Nullable A actor) {
        this.actor = actor;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void execute(float deltaTime) {
        if(actor == null) return;

        if(!isDone && movingTime == 0.0f) {
            actor.startedMoving(direction);
        }

        int oldX = actor.getPosX();
        int oldY = actor.getPosY();

        int newX = oldX + actor.getSpeed() * direction.getDx();
        int newY = oldY + actor.getSpeed() * direction.getDy();

        actor.setPosition(newX, newY);
        movingTime = movingTime + deltaTime;

        if(movingTime > duration || Math.abs(movingTime - duration) <= 1e-5) {
            stop();
        }
    }

    @Override
    public void reset() {
        isDone = false;
        movingTime = 0.0f;
    }

    public void stop(){
        isDone = true;
        actor.stoppedMoving();
    }

}
