package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.openables.Door;

public class Ventilator extends AbstractActor implements Repairable {
    Animation ventilatorAnimation;
    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("ventilator repaired", Ventilator.class);
    public Ventilator() {
        ventilatorAnimation = new Animation("sprites/ventilator.png", 32, 32, 0.1f, Animation.PlayMode.LOOP);
        setAnimation(ventilatorAnimation);
        ventilatorAnimation.stop();
    }

    @Override
    public boolean repair() {
        boolean result =  false;
        if(result == false) {
            ventilatorAnimation.play();
            result = true;
            getScene().getMessageBus().publish(VENTILATOR_REPAIRED, this);
            return true;
        }
        return false;
    }
}
