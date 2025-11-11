package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;
import sk.tuke.kpi.oop.game.openables.Door;

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
            case U:
                openDoorIntersect();
                break;
            case B:
                useKey();
                break;
            default:
                break;
        }
    }
    private void openDoorIntersect(){
        for(Object door : this.actor.getScene().getActors()){
            if(door instanceof Usable){
                if(this.actor.intersects((Actor) door)){
                    Use<?> use = new Use<>((Usable<?>) door);
                    use.scheduleForIntersectingWith(this.actor);
                }
            }
        }
    }

    private void useKey(){
        if(actor.getBackpack().peek() instanceof Usable){
            Use<?> use = new Use<>((Usable<?>) actor.getBackpack().peek());
            use.scheduleForIntersectingWith(this.actor);
        }
    }

}

