package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Helicopter extends AbstractActor {
    private Player target;

    public Helicopter() {
        Animation heliAnimation = new Animation("sprites/heli.png", 64, 64, 0.05f, Animation.PlayMode.LOOP);
        setAnimation(heliAnimation);
    }

    private void executeTask(){
        if(target == null){
            Scene scene = getScene();
            if(scene == null) return;
            target = scene.getFirstActorByType(Player.class);
            if(target == null) return;
        }

        if(this.intersects(target)){
            target.setEnergy(target.getEnergy() - 1);
            return;
        }

        int heliX = getPosX();
        int heliY = getPosY();
        int targetX = target.getPosX();
        int targetY = target.getPosY();
        int x = heliX;
        int y = heliY;

        if(heliX > targetX) x = heliX - 1;
        else if(heliX < targetX) x = heliX + 1;

        if(heliY > targetY) y = heliY - 1;
        else if(heliY < targetY) y = heliY + 1;

        this.setPosition(x, y);


    }

    public void searchAndDestroy(){
        new Loop<>(new Invoke<>(this::executeTask)).scheduleFor(this);
    }
}
