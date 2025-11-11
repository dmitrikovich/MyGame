package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

public class Locker extends AbstractActor implements Usable<Ripley> {
    Animation lockerAnimation;
    public Locker(){
        lockerAnimation = new Animation("sprites/locker.png", 16, 16);
        setAnimation(lockerAnimation);
    }
    int useLocker = 1;
    @Override
    public void useWith(Ripley actor) {
        if(useLocker == 0) return;
        useLocker--;
        Hammer hammer = new Hammer();
        actor.getScene().addActor(hammer, actor.getPosX(), actor.getPosY());
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
