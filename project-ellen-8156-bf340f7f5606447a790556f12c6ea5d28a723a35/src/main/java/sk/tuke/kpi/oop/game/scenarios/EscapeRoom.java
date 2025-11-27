package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.behaviours.Observing;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.MotherAlien;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.weapons.Gun;

public class EscapeRoom  implements SceneListener{
    private boolean ventilaatorRepaired = false;
    int ripleyGetEnergy;;

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        SceneListener.super.sceneInitialized(scene);
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        scene.follow(ripley);
        ripley.setPosition(144, 154);
        Gun gun = new Gun(100, 100);
        ripley.setFirearm(gun);

        MovableController movableController = new MovableController(ripley);
        Disposable movableControllers = scene.getInput().registerListener(movableController);

        KeeperController keeperController = new KeeperController(ripley);
        Disposable keeperControllers = scene.getInput().registerListener(keeperController);

        ShooterController shooterController = new ShooterController(ripley);
        Disposable shooterControllers = scene.getInput().registerListener(shooterController);
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) ->{
            movableControllers.dispose();
            keeperControllers.dispose();
            shooterControllers.dispose();
        });

//        scene.getMessageBus().subscribe(World.ACTOR_ADDED_TOPIC, (Alien) ->{
//
//        });





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
//    @Override
//    public void sceneCreated(@NotNull Scene scene){
//        scene.getMessageBus().subscribe(World.ACTOR_ADDED_TOPIC, (alien) ->{
//
//        });
//    }
    public static class  Factory implements ActorFactory {

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
//            if(name == null || type == null) return null;

            switch(name)
            {
                case "ellen":
                    return new Ripley();
                case "energy":
                    System.out.println("Energy added");
                    return new Energy();
                case "front door":
                    return new Door("front door", Door.Orientation.VERTICAL);
                case "back door":
                    return new Door("back door", Door.Orientation.HORIZONTAL);
                case "access card":
                    return new AccessCard();
                case "exit door":
                    return new Door("exit door", Door.Orientation.VERTICAL);
                case "locker":
                    return new Locker();
                case "alien mother":
                    return new MotherAlien();
                case "alien":
//                    if(type.equals("running")){
//                        return new Alien(new RandomlyMoving());
//                    } else {
//                        return new Alien();
//                    }
                    if(type.equals("waiting1")){
                        return   new Alien(new Observing<>(Door.DOOR_OPENED, (Door door) -> door.getName().equals("front door") || door.getName().equals("back door"), new RandomlyMoving()));
                    } else if(type.equals("waiting2")){
                        return   new Alien(new Observing<>(Door.DOOR_OPENED,  door ->  door instanceof Door && door.getName().equals("front door") || door.getName().equals("back door"), new RandomlyMoving()));
                    }
                case "ammo":
                    return new Ammo();
                default:
                    return null;
            }
        }
    }
}
