package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Point;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Openable, Usable{
    private boolean isOpened;
    Animation openAnimation;
    Animation closeAnimation;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    public Door(){
        openAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f);
        setAnimation(openAnimation);
        isOpened = false;
        openAnimation.stop();
    }

    @Override
    public void open() {
        if(isOpened) return;
        isOpened = true;
        openAnimation.setPlayMode(Animation.PlayMode.ONCE);
        openAnimation.play();


        int doorX = this.getPosX() / 16;
        int doorY = this.getPosY() / 16;

        MapTile mapTile1 = getScene().getMap().getTile(doorX, doorY);
        MapTile mapTile2 = getScene().getMap().getTile(doorX, doorY + 1);

        mapTile1.setType(MapTile.Type.CLEAR);
        mapTile2.setType(MapTile.Type.CLEAR);
        getScene().getMessageBus().publish(DOOR_OPENED, this);
    }

    @Override
    public Class<Ripley> getUsingActorClass(){
        return Ripley.class;
    }
    @Override
    public void close() {
        isOpened = false;
        openAnimation.setPlayMode(Animation.PlayMode.ONCE_REVERSED);
        openAnimation.play();

        int doorX = this.getPosX() / 16;
        int doorY = this.getPosY() / 16;

        MapTile mapTile1 = getScene().getMap().getTile(doorX, doorY);
        MapTile mapTile2 = getScene().getMap().getTile(doorX, doorY + 1);

        mapTile1.setType(MapTile.Type.WALL);
        mapTile2.setType(MapTile.Type.WALL);
        getScene().getMessageBus().publish(DOOR_CLOSED, this);
    }

    @Override
    public boolean isOpen() {
        return isOpened;
    }

    @Override
    public void useWith(Actor actor) {
        if(isOpened == true){
            this.close();
        } else {
            this.open();
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        int doorX = this.getPosX() / 16;
        int doorY = this.getPosY() / 16;

        MapTile mapTile1 = getScene().getMap().getTile(doorX, doorY);
        MapTile mapTile2 = getScene().getMap().getTile(doorX, doorY + 1);

        mapTile1.setType(MapTile.Type.WALL);
        mapTile2.setType(MapTile.Type.WALL);
    }
}
