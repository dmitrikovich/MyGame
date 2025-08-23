package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import sk.tuke.kpi.oop.game.tools.FireExtinguisher;
import sk.tuke.kpi.oop.game.tools.Hammer;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {
    private int temperature;
    private int damage;
    private boolean state = false;
    private Set<EnergyConsumer> devices = new HashSet();

    private Animation normalAnimation;
    private Animation damageAnimation;
    private Animation brokenAnimation;
    private Animation reactorOffAnimation;
    private Animation extinguisherReactor;
    public Reactor(){
        temperature = 0;
        damage = 0;

        normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        damageAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        brokenAnimation = new Animation("sprites/reactor_broken.png", 80 ,80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        reactorOffAnimation = new Animation("sprites/reactor.png", 80, 80);
        extinguisherReactor = new Animation("sprites/reactor_extinguished.png");
        setAnimation(reactorOffAnimation);
    }
    public int getTemperature() {
        return temperature;
    }
    public int getDamage() {
        return damage;
    }

    public void increaseTemperature(int temperature){
        if(this.damage == 100 || !isOn()) return;
        if(temperature < 0) {
            decreaseTemperature(-temperature);
            return;
        }

        if(this.damage < 33) {
            this.temperature += temperature;
        } else if(this.damage >= 33 && this.damage <= 66){
            this.temperature += Math.round(temperature * 1.5f);
        }  else {
            this.temperature += temperature * 2;
        }

        if(this.temperature >= 6000){
            this.damage = 100;
            turnOff();
        }
        else if(this.temperature > 2000){
             this.damage = (int) ((double) (this.temperature - 2000) / 40);
        }


        updateAnimation();
    }

    public void decreaseTemperature(int decrement){
        if(this.damage == 100 || !isOn()) return;
        if(decrement < 0) return;
        if(this.damage >= 50){
            this.temperature -= decrement / 2;
        } else{
            this.temperature -= decrement;
        }
        if(temperature < 0) temperature = 0;
        updateAnimation();
    }

    public void updateAnimation(){
        if(this.temperature >= 6000 || damage == 100){
            setAnimation(brokenAnimation);
        } else if(this.temperature > 4000){
//            damageAnimation.setFrameDuration(0.1f);
            damageAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.1f - 0.00005f * damage, Animation.PlayMode.LOOP_PINGPONG);
            setAnimation(damageAnimation);
        } else setAnimation(normalAnimation);
    }

    @Override
    public boolean repair(){
        if(this.damage > 0 && this.damage < 100){
            if(this.damage <= 50){
                this.damage = 0;
                this.temperature = 0;
            } else if(this.damage > 50){
                this.damage = this.damage - 50;
                int newTemperature = this.damage * 40 + 2000 ;
                if(newTemperature < temperature) temperature = newTemperature;
            }
            updateAnimation();
            return true;
        }
        return false;
    }

    public boolean extinguish(){
        if( damage == 100){
            temperature -= 4000;
            if(temperature < 0) temperature = 0;
            setAnimation(extinguisherReactor);
            return  true;
        }
        return false;
    }

    public void turnOn(){
        state = true;
        devices.forEach((device) -> {
            device.setPowered(true);
            System.out.println(device);
            System.out.println("Powered on");
        });
        updateAnimation();
    }

    public  void turnOff(){
        state = false;
        devices.forEach((device) -> {device.setPowered(false);});
        setAnimation(reactorOffAnimation);
    }
    public boolean isOn(){
        return state;
    }

    public void addDevice(EnergyConsumer device){
        if(devices == null) return;
        if(state) device.setPowered(true);
        devices.add(device);
    }

    public void removeDevice(EnergyConsumer device){
        if(devices == null) return;
        if(state) device.setPowered(false);
        devices.remove(device);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);

    }
}
