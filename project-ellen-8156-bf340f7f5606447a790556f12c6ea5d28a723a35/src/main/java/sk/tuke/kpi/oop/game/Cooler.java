package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements  Switchable{
    private Animation fanActiveAnimation;
    private Animation fanInactiveAnimation;
    private Reactor reactor;
    private boolean state;

    public Cooler( Reactor reactor ) {
        fanActiveAnimation = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
        fanInactiveAnimation = new Animation("sprites/fan.png", 32, 32, 0.0f);
        this.reactor = reactor;
        state = false;
        setAnimation(fanInactiveAnimation);
    }
    public void turnOn(){
        state = true;
        setAnimation(fanActiveAnimation);
    }
    public void turnOff(){
        state = false;
        setAnimation(fanInactiveAnimation);
    }
    public boolean isOn(){
        return state;
    }
    public void coolReactor(){
        if(reactor != null && isOn()){
            reactor.decreaseTemperature(1);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }
}
