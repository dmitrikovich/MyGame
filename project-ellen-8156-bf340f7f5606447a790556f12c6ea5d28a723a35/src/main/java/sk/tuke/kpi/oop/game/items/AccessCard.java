package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class AccessCard extends AbstractActor implements Collectible, Usable<LockedDoor> {
    Animation keyAnimation;
    LockedDoor lockedDoor;
    public AccessCard() {
        keyAnimation = new Animation("sprites/key.png", 16 ,16, 0.0f);
        setAnimation(keyAnimation);
    }

    @Override
    public void useWith(LockedDoor actor) {
        actor.unlock();
    }

    @Override
    public Class<LockedDoor> getUsingActorClass() {
        return LockedDoor.class;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(
            new Invoke<>(() -> {
                Ripley ripley = scene.getFirstActorByType(Ripley.class);
                if (ripley == null) return;
                if (ripley.intersects(this)) {
                    ripley.getBackpack().add(this);
                    scene.removeActor(this);  // убираем карту со сцены
                }
            })
        ).scheduleFor(this);
    }
}
