package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.characters.Alien;

public class RandomlyMoving implements Behaviour<Movable>{
    public RandomlyMoving() {

    }


    @Override
    public void setUp(Movable alien) {
        new Loop<>(
            new ActionSequence<>(
                new Invoke<>(() ->{
                    Direction direction1 = Direction.NONE;
                    int coordinateX = (int) (Math.random() * 3) - 1;
                    int coordinateY = (int) (Math.random() * 3) - 1;
                    for(Direction direction : Direction.values()){
                        if(direction.getDx() == coordinateX && direction.getDy() == coordinateY){
                            direction1 = direction;
                        }
                    }
//                        System.out.println(direction1);
                    if(alien instanceof Alien){
                        new Move<>(direction1, 0.5f).scheduleFor(alien);
                    }
                    if(direction1 != Direction.NONE){
                        alien.getAnimation().setRotation(direction1.getAngle());
                    }
                }),
                new Wait<>(1.5f)
            )
        ).scheduleFor(alien);
    }
}
