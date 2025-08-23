package sk.tuke.kpi.oop.game;
// Сам делал
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements  Switchable, EnergyConsumer{
    private Animation lightOn;
    private Animation lightOff;
    private boolean electricity;
    private boolean state;

    public Light(){
        electricity = false;
        state = false;
        lightOn = new Animation("sprites/light_on.png", 16, 16, 0,  Animation.PlayMode.LOOP_PINGPONG);
        lightOff = new Animation("sprites/light_off.png", 16, 16, 0,  Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(lightOff);
    }

    private void checkLight() {
        if(state && electricity){
            setAnimation(lightOn);
        }
        else {
            setAnimation(lightOff);
        }
    }

    public void toggle( ){
        state = !state;
        checkLight();
    }

    @Override
    public void turnOn() {
        state = true;
        checkLight();
    }

    @Override
    public void turnOff() {
        state = false;
        checkLight();
    }

    @Override
    public boolean isOn() {
        return state;
    }

    @Override
    public void setPowered(boolean powered) {
        this.electricity = powered;
        checkLight();
    }
}
