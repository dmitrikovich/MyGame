package sk.tuke.kpi.oop.game;
// Сам делал
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor {
    private Animation hammerAnimation;
    private int usingHammerCount;

    public Hammer(){
        usingHammerCount = 1;
        hammerAnimation = new Animation("sprites/hammer.png", 16, 16, 0.2f,  Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(hammerAnimation);
    }

    public Hammer(int usingHammerCount){
        this.usingHammerCount = usingHammerCount;
        hammerAnimation = new Animation("sprites/hammer.png", 16, 16, 0.2f,  Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(hammerAnimation);
    }

    public int getUsingHammerCount() {
        return usingHammerCount;
    }

    public void use(){
        if(usingHammerCount > 0){
            usingHammerCount--;
           System.out.println(usingHammerCount);
        }
        if(usingHammerCount <= 0){
            Scene scene = getScene();
            if(scene != null) scene.removeActor(this);
        }
    }
}
