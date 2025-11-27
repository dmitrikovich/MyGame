package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;

public class Fire<A extends Armed> extends AbstractAction<A> {
    public Fire(){

    }

    @Override
    public void execute(float deltaTime) {
        if (getActor() == null || getActor().getScene() == null || getActor().getFirearm() == null){
            setDone(true);
            return;
        }
        Fireable fireable = getActor().getFirearm().fire();
        if(fireable == null){
            setDone(true);
            return;
        }
        int dx = Direction.fromAngle(getActor().getAnimation().getRotation()).getDx();
        int dy = Direction.fromAngle(getActor().getAnimation().getRotation()).getDy();

        getActor().getScene().addActor((Actor) fireable, getActor().getPosX() + 14 + dx*24 , getActor().getPosY() + 10 + dy*24);
        fireable.startedMoving(Direction.fromAngle(getActor().getAnimation().getRotation()));
        new Move<Fireable>(Direction.fromAngle(getActor().getAnimation().getRotation()), 10000000).scheduleFor(fireable);
        setDone(true);
    }
}
