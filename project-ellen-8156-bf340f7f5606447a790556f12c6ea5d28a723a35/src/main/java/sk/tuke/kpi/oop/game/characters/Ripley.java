package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.items.Backpack;

import static sk.tuke.kpi.gamelib.graphics.Animation.PlayMode.LOOP_PINGPONG;

public class Ripley extends AbstractActor implements Movable, Keeper {
    private final int speed;
    private Animation animation;
    private Animation ripleyDied = new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
    private int energy;
    private Backpack backpack = new Backpack("Ripley's backpack", 10);
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);
    public Ripley() {
        super("Ellen");
        speed = 2;
        energy = 50;
        animation = new Animation("sprites/player.png", 32, 32, 0.1f, LOOP_PINGPONG);
//        setAnimation(animation);
        setAnimation(animation);
        stoppedMoving();
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        if (energy < 0 || energy > 100) return;
        if(energy == 0){
            getScene().getMessageBus().publish(RIPLEY_DIED, this);
            setAnimation(ripleyDied);
        }
        this.energy = energy;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        animation.setRotation(direction.getAngle());
        setAnimation(animation);
        getAnimation().play();
    }

    @Override
    public void stoppedMoving() {
        this.getAnimation().stop();
    }

    @Override
    public Backpack getBackpack() {
        return backpack;
    }
    public void showRipleyState(){
        int windowHeight = this.getScene().getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        this.getScene().getGame().getOverlay().drawText(" | Energy: " + this.getEnergy(), 90, yTextPos);
        this.getScene().getGame().getOverlay().drawText(" Ripley's Backpack ", 500, 40);
    }
}
