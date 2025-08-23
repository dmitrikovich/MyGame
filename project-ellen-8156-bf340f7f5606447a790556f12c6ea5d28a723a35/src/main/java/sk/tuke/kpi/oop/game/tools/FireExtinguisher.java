package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor>
{
    private Animation animation;

    public FireExtinguisher() {
        super(1);
        animation = new Animation("sprites/extinguisher.png");
        setAnimation(animation);
    }

    @Override
    public void useWith(Reactor reactor){
        super.useWith(reactor);
        System.out.println("FireExtinguisher is used");
    }
}
