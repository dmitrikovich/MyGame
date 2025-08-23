package sk.tuke.kpi.oop.game;
// САм
import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {
    private Animation switchAnimation;
    private Switchable device;

    public PowerSwitch(Switchable device){
        switchAnimation = new Animation("sprites/switch.png", 16, 16, 0.0f, Animation.PlayMode.ONCE);
        setAnimation(switchAnimation);
        this.device = device;
    }

    public Switchable getDevice(){
        return device;
    }

    public void switchOn(){
        device.turnOn();
        getAnimation().setTint(Color.WHITE);
    }

    public void switchOff(){
        device.turnOff();
        getAnimation().setTint(Color.GRAY);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if(device != null){
            if(device.isOn()) getAnimation().setTint(Color.WHITE);
            else getAnimation().setTint(Color.GRAY);
        }
    }
}
