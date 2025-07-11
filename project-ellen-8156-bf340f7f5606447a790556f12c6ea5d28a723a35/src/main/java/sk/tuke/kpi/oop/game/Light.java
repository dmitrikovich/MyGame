package sk.tuke.kpi.oop.game;
// Сам делал
import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor {
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

    public void toggle( ){
        state = !state;
        if(state && electricity){
            setAnimation(lightOn);
        }
        else {
            setAnimation(lightOff);
        }
    }

    public void setElectricityFlow(boolean electricity){
        this.electricity = electricity;
        if(state && electricity){
            setAnimation(lightOn);
        }
        else {
            setAnimation(lightOff);
        }
    }

}
