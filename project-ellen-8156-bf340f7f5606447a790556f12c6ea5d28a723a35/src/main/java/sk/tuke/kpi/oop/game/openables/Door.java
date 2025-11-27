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
    Animation openVAnimation;
    Animation openHAnimation;

    Animation closeAnimation;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    private Orientation orientation;
    private String name;

    public enum Orientation{
        HORIZONTAL,
        VERTICAL
    }

    public Door(){
//        openVAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f);
//        setAnimation(openVAnimation);
//        isOpened = false;
//        openVAnimation.stop();
    }

    public Door(String name, Orientation orientation){
        this.name = name;
        this.orientation = orientation;

        if(orientation == Orientation.HORIZONTAL){
            openHAnimation = new Animation("sprites/hdoor.png", 32, 16, 0.1f);
            setAnimation(openHAnimation);
            isOpened = false;
            openHAnimation.stop();

        } else if(orientation == Orientation.VERTICAL){
            openVAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f);
            setAnimation(openVAnimation);
            isOpened = false;
            openVAnimation.stop();
        }
    }


    @Override
    public void open() {
        if(isOpened) return;

        int doorX = this.getPosX() / 16;
        int doorY = this.getPosY() / 16;

        if(orientation == Orientation.HORIZONTAL){
            isOpened = true;
            openHAnimation.setPlayMode(Animation.PlayMode.ONCE);
            openHAnimation.play();
            MapTile mapTile1 = getScene().getMap().getTile(doorX, doorY);
            MapTile mapTile2 = getScene().getMap().getTile(doorX + 1, doorY);

            mapTile1.setType(MapTile.Type.CLEAR);
            mapTile2.setType(MapTile.Type.CLEAR);
        } else if(orientation == Orientation.VERTICAL){
            isOpened = true;
            openVAnimation.setPlayMode(Animation.PlayMode.ONCE);
            openVAnimation.play();
            MapTile mapTile1 = getScene().getMap().getTile(doorX, doorY);
            MapTile mapTile2 = getScene().getMap().getTile(doorX, doorY + 1);

            mapTile1.setType(MapTile.Type.CLEAR);
            mapTile2.setType(MapTile.Type.CLEAR);
        }

        getScene().getMessageBus().publish(DOOR_OPENED, this);
    }

    @Override
    public Class<Ripley> getUsingActorClass(){
        return Ripley.class;
    }
    @Override
    public void close() {

        int doorX = this.getPosX() / 16;
        int doorY = this.getPosY() / 16;

//        MapTile mapTile1 = getScene().getMap().getTile(doorX, doorY);
//        MapTile mapTile2 = getScene().getMap().getTile(doorX + 1, doorY);

        if(orientation == Orientation.HORIZONTAL){
            isOpened = false;
            openHAnimation.setPlayMode(Animation.PlayMode.ONCE_REVERSED);
            openHAnimation.play();
            MapTile mapTile1 = getScene().getMap().getTile(doorX, doorY);
            MapTile mapTile2 = getScene().getMap().getTile(doorX, doorY);
            mapTile1.setType(MapTile.Type.WALL);
            mapTile2.setType(MapTile.Type.WALL);
        } else if(orientation == Orientation.VERTICAL){
            isOpened = false;
            openVAnimation.setPlayMode(Animation.PlayMode.ONCE_REVERSED);
            openVAnimation.play();
            MapTile mapTile1 = getScene().getMap().getTile(doorX, doorY);
            MapTile mapTile2 = getScene().getMap().getTile(doorX, doorY + 1);
            mapTile1.setType(MapTile.Type.WALL);
            mapTile2.setType(MapTile.Type.WALL);
        }



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

        if (orientation == Orientation.HORIZONTAL) {
            MapTile mapTile1 = getScene().getMap().getTile(doorX, doorY);
            MapTile mapTile2 = getScene().getMap().getTile(doorX + 1, doorY);

            mapTile1.setType(MapTile.Type.WALL);
            mapTile2.setType(MapTile.Type.WALL);
        }
        else if (orientation == Orientation.VERTICAL) {
            MapTile mapTile1 = getScene().getMap().getTile(doorX, doorY);
            MapTile mapTile2 = getScene().getMap().getTile(doorX, doorY + 1);

            mapTile1.setType(MapTile.Type.WALL);
            mapTile2.setType(MapTile.Type.WALL);
        }
    }

    public @NotNull String getName(){
        return name;
    }
}
