package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.actions.Fire;
import sk.tuke.kpi.oop.game.characters.Armed;

import java.util.List;

public class ShooterController extends AbstractActor implements KeyboardListener{
    private Armed armed;
    public ShooterController(Armed armed){
        this.armed = armed;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        KeyboardListener.super.keyPressed(key);
        if (key == Input.Key.SPACE) {
            System.out.println("Space pressed");
            new Fire<>().scheduleFor(armed);
        }


    }
}
