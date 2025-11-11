package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;
import sk.tuke.kpi.oop.game.Repairable;

public class Wrench extends BreakableTool<DefectiveLight> implements Collectible{

    public Wrench() {
        super(2);
        Animation animation = new Animation("sprites/wrench.png");
        setAnimation(animation);
    }

    @Override
    public Class<DefectiveLight> getUsingActorClass(){
        return DefectiveLight.class;
    }
    public void useWith(DefectiveLight actor) {
        super.useWith(actor);
        actor.repair();
    }
}
