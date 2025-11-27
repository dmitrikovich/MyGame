package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class MotherAlien extends Alien {
    Animation moherAnimation;
    public MotherAlien() {
        moherAnimation = new Animation("sprites/mother.png", 112, 162, 0.2f, Animation.PlayMode.LOOP);
        setAnimation(moherAnimation);

    }
}
