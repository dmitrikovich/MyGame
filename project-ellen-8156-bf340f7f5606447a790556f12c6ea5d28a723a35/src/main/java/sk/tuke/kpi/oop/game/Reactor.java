package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Reactor extends AbstractActor {
    private int temperature;
    private int damage;
    private Animation normalAnimation;
    private Animation damageAnimation;
    private Animation brokenAnimation;

    public Reactor(){
        temperature = 0;
        damage = 0;

        normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        damageAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        brokenAnimation = new Animation("sprites/reactor_broken.png", 80 ,80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(normalAnimation);
    }
    public int getTemperature() {
        return temperature;
    }
    public int getDamage() {
        return damage;
    }

    public void increaseTemperature(int temperature){
        if(this.damage == 100) return;
        if(temperature < 0) return;

        if(this.damage < 33) {
            this.temperature += temperature;
        } else if(this.damage >= 33 && this.damage <= 66){
            this.temperature += Math.round(temperature * 1.5f);
        }  else {
            this.temperature += temperature * 2;
        }

        if(this.temperature >= 6000) this.damage = 100;
        else if(this.temperature > 2000){
             this.damage = (int) ((double) (this.temperature - 2000) / 40);
        }


        updateAnimation();
    }

    public void decreaseTemperature(int decrement){
        if(this.damage == 100) return;
        if(decrement < 0) return;
        if(this.damage >= 50){
            this.temperature -= decrement / 2;
        } else{
            this.temperature -= decrement;
        }

        updateAnimation();
    }

    public void updateAnimation(){
        if(this.temperature >= 6000){
            setAnimation(brokenAnimation);
        } else if(this.temperature > 4000){
            System.out.println(damageAnimation.getFrameDuration());
//            damageAnimation.setFrameDuration(0.1f);
            System.out.println(damageAnimation.getFrameDuration());
            setAnimation(damageAnimation);
        } else setAnimation(normalAnimation);
    }
}
