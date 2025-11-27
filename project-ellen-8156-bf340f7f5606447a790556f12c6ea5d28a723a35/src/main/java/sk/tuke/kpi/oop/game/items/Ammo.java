package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Armed;

import java.util.List;

public class Ammo extends AbstractActor implements Usable<Armed> {
    Animation animationAmmo;
    public Ammo() {
        animationAmmo = new Animation("sprites/ammo.png", 16, 16);
        setAnimation(animationAmmo);
    }
    @Override
    public void useWith(Armed actor) {
        System.out.println(actor.getFirearm().getAmmo());
        if(actor != null && getScene() != null &&
            actor.getFirearm().getAmmo() < actor.getFirearm().getMaxAmmo()){
            actor.getFirearm().reload(50);
            System.out.println(actor.getFirearm().getAmmo());
            this.getScene().removeActor(this);
        } else {
            return;
        }
    }

    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new When<>(
            () -> {
                return findArmed(scene) != null;
            },
            new Invoke<>(() -> {
                List<Actor> actors = scene.getActors();
                for(Actor actor : actors){
                    if(actor instanceof Armed){
                        new When<>(
                            () -> {
                                return actor.intersects(this);
                            },
                            new Invoke<>(() -> {

                                new Use<>(this).scheduleFor((Armed) actor);
                            })
                        ).scheduleFor(this);
                    }
                }
//                if(actor == null) return;
            })
        ).scheduleFor(this);
    }

    private Armed findArmed(Scene scene) {
        List<Actor> actors = scene.getActors();
        for(Actor actor : actors){
            if(actor instanceof Armed){
                return (Armed) actor;
            }
        }
        return null;
    }

    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }
}
