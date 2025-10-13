package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Take<A extends Keeper> extends AbstractAction<A> {


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
        try {
            Collectible collectibleItem = scene.getActors().stream()
                .filter(collectible -> collectible instanceof Collectible)
                .filter(collectible -> collectible.intersects(actor))
                .map(collectible -> (Collectible) collectible)
                .findFirst().orElse(null);

            if(collectibleItem != null) {
                Backpack backpack = actor.getBackpack();
                if(backpack == null) {
                    setDone(true);
                    return;
                }
                backpack.add(collectibleItem);
                scene.removeActor(collectibleItem);
            }


        } catch (IllegalStateException ex) {
            String msg  = ex.getMessage();
            scene.getGame().getOverlay().drawText(msg, 0, 0).showFor(2);
        }

        setDone(true);
    }
}
