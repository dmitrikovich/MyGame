package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;

public class KeeperController implements KeyboardListener {
    private final Keeper actor;

    public KeeperController(Keeper actor) {
        this.actor = actor;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        KeyboardListener.super.keyPressed(key);

        switch (key) {
            case ENTER:
                new Take<>().scheduleFor(actor);
                break;
            case BACKSPACE:
                new Drop<>().scheduleFor(actor);
                break;
            case S:
                new Shift<>().scheduleFor(actor);
                break;
            default:
                break;
        }

    }
}

