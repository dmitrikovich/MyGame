package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {
    private float time;
    private Animation bombActivation;
    private Animation bombSmallExplosion;
    private boolean activated  = false;

    public TimeBomb(float time){
        this.time = time;
        this.activated = false;
        setAnimation(new Animation("sprites/bomb.png"));
        bombActivation = new Animation("sprites/bomb_activated.png", 16, 16, time/6, Animation.PlayMode.ONCE);
        bombSmallExplosion = new Animation("sprites/small_explosion.png", 16, 16, 0.1f, Animation.PlayMode.ONCE);

    }
    public void activate(){
        if(this.activated) return;
        this.activated = true;

        new ActionSequence<>(
            new Invoke<>(()->{setAnimation(bombActivation);}),
            new Wait<>(time),
            new Invoke<>(()->{
                setAnimation(bombSmallExplosion);
                extraAction();
            }),
            new Wait<>(0.8f),
            new Invoke<>(()->{
                Scene scene = getScene();
                if(scene != null) {
                    scene.removeActor(this);
                }
            })
        ).scheduleFor(this);
    }
    public boolean isActivated(){
        return this.activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void extraAction(){
    }


}

