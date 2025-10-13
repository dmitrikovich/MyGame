package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

abstract class BreakableTool<A extends Actor> extends AbstractActor implements Usable<A> {
    private int remainingUses;

    public BreakableTool(int remainingUses) {
        this.remainingUses = remainingUses;
    }

    @Override
    public void useWith(A actor) {
        remainingUses--;
        Scene scene = getScene();
        if (scene != null && remainingUses <= 0) scene.removeActor(this);
    }

    public int getRemainingUses() {
        return remainingUses;
    }
}
