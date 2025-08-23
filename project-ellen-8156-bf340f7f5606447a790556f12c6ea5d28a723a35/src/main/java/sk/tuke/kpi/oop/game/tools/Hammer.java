package sk.tuke.kpi.oop.game.tools;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.Repairable;

public class Hammer extends BreakableTool<Repairable> {
    private Animation hammerAnimation;

    public Hammer(){
        super(1);
        hammerAnimation = new Animation("sprites/hammer.png", 16, 16, 0.2f,  Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(hammerAnimation);
    }

    public Hammer(int remainingUses){
        super(remainingUses);
        hammerAnimation = new Animation("sprites/hammer.png", 16, 16, 0.2f,  Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(hammerAnimation);
    }

    @Override
    public void useWith(Repairable actor) {
        super.useWith(actor);
        actor.repair();
        System.out.println("Hammer is used");
    }
}
