package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends AbstractActor {
    private int usingFireExtinguisherCount;
    private Animation animation;

    public FireExtinguisher() {
        usingFireExtinguisherCount = 1;
        animation = new Animation("sprites/extinguisher.png");
        setAnimation(animation);
    }

    public void use(){
        if(usingFireExtinguisherCount > 0){
            usingFireExtinguisherCount--;
            System.out.println(usingFireExtinguisherCount);
        }
        if(usingFireExtinguisherCount <= 0){
            Scene scene = getScene();
            if(scene != null) scene.removeActor(this);
        }
    }

    public int getUsingFireExtinguisherCount() {
        return usingFireExtinguisherCount;
    }
}
