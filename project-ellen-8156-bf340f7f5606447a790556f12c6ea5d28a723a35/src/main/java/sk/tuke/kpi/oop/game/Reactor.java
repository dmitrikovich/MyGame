package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Random;

public class Reactor extends AbstractActor {
    private int temperature;
    private int damage;
    private boolean state = false;
    private Light light;

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
        if(this.damage == 100 || !isRunning()) return;
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
        if(this.damage == 100 || !isRunning()) return;
        if(decrement < 0) return;
        if(this.damage >= 50){
            this.temperature -= decrement / 2;
        } else{
            this.temperature -= decrement;
        }

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
//    сам
    public void repairWidth(Hammer hammer){
        if(hammer != null && this.damage > 0 && this.damage < 100){
            hammer.use();
            if(this.damage <= 50){
                this.damage = 0;
                this.temperature = 0;
            } else if(this.damage > 50){
                this.damage = this.damage - 50;
                int newTemperature = this.damage * 40 + 2000 ;
                if(newTemperature < temperature) temperature = newTemperature;
            }
            updateAnimation();
        }
    }

    public void extinguishWith(FireExtinguisher extinguisher){
        if(extinguisher != null && damage == 100){
            extinguisher.use();
            temperature -= 4000;
            if(temperature < 0) temperature = 0;
            setAnimation(extinguisherReactor);
        }
    }

    public void turnOn(){
        state = true;
        if(light != null) light.setElectricityFlow(true);
        updateAnimation();
    }

    public  void turnOff(){
        state = false;
        if(light != null) light.setElectricityFlow(false);
        setAnimation(reactorOffAnimation);
    }
    public boolean isRunning(){
        return state;
    }

    public void addLight(Light light){
        if(light != null) this.light = light;
        if(state) this.light.setElectricityFlow(true);
    }

    public void removeLight(){
        this.light.setElectricityFlow(false);
        this.light = null;
    }
}
