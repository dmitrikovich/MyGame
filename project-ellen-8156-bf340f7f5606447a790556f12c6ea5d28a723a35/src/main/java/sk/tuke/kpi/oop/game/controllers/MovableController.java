package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.Map;

public class MovableController implements KeyboardListener {

    private final Movable actor;
    private final Map<Input.Key, Direction> map = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST),
        Map.entry(Input.Key.RIGHT, Direction.EAST)
    );
    private Move<Movable> lastMove;

    public  MovableController(Movable actor) {
        this.actor = actor;
        lastMove = null;
    }

    @Override
    public void keyPressed(@NotNull  Input.Key key) {
        KeyboardListener.super.keyPressed(key);

        if(!map.containsKey(key)) return;

        Direction direction = map.get(key);

        if(lastMove != null){
            lastMove.stop();
            Direction other = lastMove.getDirection();
            direction = direction.combine(other);
        }
        Move<Movable> move = new Move<>(direction, Float.MAX_VALUE);
        move.scheduleFor(actor);
        lastMove = move;
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        KeyboardListener.super.keyReleased(key);
        if(!map.containsKey(key)) return;
        if(lastMove != null) {
            lastMove.stop();
            Direction releasedDirection = map.get(key);
            Direction moveDirection = lastMove.getDirection();

            Direction newDirection = moveDirection.split(releasedDirection);

            if(newDirection == Direction.NONE) lastMove = null;
            else{
                Move<Movable> move = new Move<>(newDirection, Float.MAX_VALUE);
                move.scheduleFor(actor);
                lastMove = move;
            }
        }
    }
}
