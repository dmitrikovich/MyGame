package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class MissionImpossible implements SceneListener {
    private boolean ventilaatorRepaired = false;
    int ripleyGetEnergy;;

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        SceneListener.super.sceneInitialized(scene);
        Ripley ripley = scene.getFirstActorByType(Ripley.class);


        scene.getMessageBus().subscribe( Door.DOOR_OPENED , (Ripley) ->{
            new Loop<>(
                new ActionSequence<>(
                    new Invoke<>(() -> {
                        if(ventilaatorRepaired) return;
                        ripleyGetEnergy = ripley.getEnergy();
                        ripley.setEnergy(ripleyGetEnergy - 5);
                    }),
                    new Wait<>(1)
                )
            ).scheduleFor(ripley);
        });
        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, (Ripley) ->{
            ventilaatorRepaired = true;
        });
        MovableController movableController = new MovableController(ripley);
        Disposable movableControllers = scene.getInput().registerListener(movableController);

        KeeperController keeperController = new KeeperController(ripley);
        Disposable keeperControllers = scene.getInput().registerListener(keeperController);


        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) ->{
                movableControllers.dispose();
                keeperControllers.dispose();
        });


//        Ripley ripley = new Ripley();
//        scene.addActor(ripley, 0 ,0);
//
//        Energy energy = new Energy();
//        scene.addActor(energy, 100 ,100);
//

    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        SceneListener.super.sceneUpdating(scene);

        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        if(ripley == null) return;
        ripley.showRipleyState();

        scene.getGame().pushActorContainer(ripley.getBackpack());


    }
    public static class  Factory implements ActorFactory{

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {

            switch(name)
            {
                case "ellen":
                    return new Ripley();
                case "energy":
                    return new Energy();
                case "door":
                    return new LockedDoor();
                case "access card":
                    return new AccessCard();
                case "locker":
                    return new Locker();
                case "ventilator":
                    return new Ventilator();
                default:
                    return null;
            }
        }
    }
}


