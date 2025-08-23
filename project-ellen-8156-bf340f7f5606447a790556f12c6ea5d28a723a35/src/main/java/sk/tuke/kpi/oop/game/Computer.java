package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {
    private Animation normalAnimation;
    private Animation offAnimation;
    private boolean electricity;

    public Computer() {
        electricity = false;
        normalAnimation = new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
        offAnimation = new Animation("sprites/computer.png", 80, 48, 0f, Animation.PlayMode.ONCE);
        setAnimation(offAnimation);
    }
    public int add(int a, int b){
        if(!electricity) return 0;
        return a + b;
    }
    public float add(float a, float b){
        if(!electricity) return 0;
        return a + b;
    }
    public int sub(int a, int b){
        if(!electricity) return 0;
        return a - b;
    }
    public float sub(float a, float b){
        if(!electricity) return 0;
        return a - b;
    }

    @Override
    public void setPowered(boolean powered) {
        electricity = powered;
        if(electricity) setAnimation(normalAnimation);
        else setAnimation(offAnimation);
    }
}
