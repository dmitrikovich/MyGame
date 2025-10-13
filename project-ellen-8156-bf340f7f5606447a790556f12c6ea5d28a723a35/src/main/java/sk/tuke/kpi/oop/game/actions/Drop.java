package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop<A extends Keeper> extends AbstractAction<A> {
    @Override
    public void execute(float deltaTime) {
        A actor = getActor();
        if(actor == null) {
            setDone(true);
            return;
        }
        Scene scene = actor.getScene();
        if(scene == null) {
            setDone(true);
            return;
        }

        Backpack backpack = actor.getBackpack();
        if(backpack == null) {
            setDone(true);
            return;
        }

        Collectible item = backpack.peek();
        if(item == null) {
            setDone(true);
            return;
        }
        backpack.remove(item);

        scene.addActor(item, actor.getPosX(), actor.getPosY());
        setDone(true);
    }
}
