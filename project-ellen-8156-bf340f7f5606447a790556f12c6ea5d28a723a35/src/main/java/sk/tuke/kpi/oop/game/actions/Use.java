package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<A extends Actor> extends AbstractAction<A> {

    private final Usable<A> actor;

    public Use(Usable<A> actor) {
        this.actor = actor;
    }

    @Override
    public void execute(float deltaTime) {
        if(actor == null || getActor() == null) return;
        actor.useWith(getActor());
        setDone(true);
    }
}
