package sk.tuke.kpi.oop.game;
// САм
import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Controller extends AbstractActor {
    private Animation switchAnimation;
    private Reactor reactor;

    public Controller(Reactor reactor){
        switchAnimation = new Animation("sprites/switch.png", 16, 16, 0,  Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(switchAnimation);
        this.reactor = reactor;
    }

    public void toggle(){
        if(reactor == null || reactor.getDamage() >= 6000) return;

        if(reactor.isRunning()){
            reactor.turnOff();
        } else {
            reactor.turnOn();
        }
    }
}
