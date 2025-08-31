package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import static sk.tuke.kpi.gamelib.graphics.Animation.PlayMode.LOOP_PINGPONG;

public class Ripley extends AbstractActor {

    public Ripley() {
        super("Ellen");
        Animation animation = new Animation("sprites/player.png", 32, 32, 0.1f, LOOP_PINGPONG);
        setAnimation(animation);
    }

}
