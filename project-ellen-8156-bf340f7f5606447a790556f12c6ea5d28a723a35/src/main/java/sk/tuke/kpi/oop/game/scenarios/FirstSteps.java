package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;

public class FirstSteps implements SceneListener {

    Hammer hammer = new Hammer();
    Wrench wrench = new Wrench();
    FireExtinguisher fireExtinguisher = new FireExtinguisher();

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        SceneListener.super.sceneInitialized(scene);

//        Ripley ripley = new Ripley();
//        scene.addActor(ripley, 0 ,0);

//        Energy energy = new Energy();
//        scene.addActor(energy, 100 ,100);

//        scene.addActor(wrench, 150 ,150);
//        scene.addActor(hammer, 200 ,200);
//        scene.addActor(fireExtinguisher, 300, 300);

//        Backpack backpack = ripley.getBackpack();
//        backpack.add(hammer);
//        backpack.add(wrench);
//        backpack.add(fireExtinguisher);

//        MovableController movableController = new MovableController(ripley);
//        scene.getInput().registerListener(movableController);
//
//        KeeperController keeperController = new KeeperController(ripley);
//        scene.getInput().registerListener(keeperController);
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        SceneListener.super.sceneUpdating(scene);

        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;

        Ripley ripley = scene.getFirstActorByType(Ripley.class);


        if(ripley == null) return;

        scene.getGame().getOverlay().drawText(" | Energy: " + ripley.getEnergy(), 90, yTextPos);
        scene.getGame().getOverlay().drawText(" Ripley's Backpack ", 500, 40);
        scene.getGame().pushActorContainer(ripley.getBackpack());



    }
}
