package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Energy extends AbstractActor implements Usable<Ripley> {
    public Energy() {
        Animation animation = new Animation("sprites/energy.png");
        setAnimation(animation);
    }

    @Override
    public void useWith(Ripley actor) {
        actor.setEnergy(100);

        Scene scene = this.getScene();
        if(scene == null) return;
        scene.removeActor(this);
    }
    @Override
    public Class<Ripley> getUsingActorClass(){
        return Ripley.class;
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new When<>(
            () -> {
                return scene.getFirstActorByType(Ripley.class) != null;
            },
            new Invoke<>(() -> {
                Ripley ripley = scene.getFirstActorByType(Ripley.class);
                if(ripley == null) return;

                new When<>(
                    () -> {
                        return ripley.intersects(this);
                    },
                    new Invoke<>(() -> {

                        new Use<>(this).scheduleFor(ripley);
                    })
                ).scheduleFor(this);
            })
        ).scheduleFor(this);
    }
}
