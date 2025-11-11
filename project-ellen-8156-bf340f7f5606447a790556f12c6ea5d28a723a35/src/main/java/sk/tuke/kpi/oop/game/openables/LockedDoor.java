package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;

public class LockedDoor extends Door {
    boolean locked = true;
    Door door;
    public LockedDoor() {

    }

    public void lock() {
        locked = true;
        close();
    }
    public void unlock(){
        locked = false;
        open();
    }
    public boolean isLocked(){
        return locked;
    }

    @Override
    public void useWith(Actor actor) {
//        if (!(actor instanceof Ripley)) return;
//        Ripley ripley = (Ripley) actor;
//
//        Backpack backpack = ripley.getBackpack();
//        List<Collectible> backpackItems = backpack.getContent();
//        backpackItems.forEach(backpackItem -> {
//            if(backpackItem instanceof AccessCard ) {
//                unlock();
//            } else {
//                return;
//            }
//        });
       if(!isLocked()) super.useWith(actor);
    }
}
