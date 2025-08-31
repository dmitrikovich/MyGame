package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.awt.geom.Rectangle2D;
import java.util.List;

public class Teleport extends AbstractActor {
    private Animation liftAnimation;
    private Teleport destinationTeleport;
    private boolean teleported;

    public Teleport(Teleport destinationTeleport) {
        liftAnimation = new Animation("sprites/lift.png", 48, 48);
        setAnimation(liftAnimation);
        setDestinationTeleport(destinationTeleport);
    }

    public Teleport() {
        liftAnimation = new Animation("sprites/lift.png", 48, 48);
        setAnimation(liftAnimation);
    }

    private Teleport getDestination(){
        return destinationTeleport;
    }

    public void setDestinationTeleport(Teleport destinationTeleport) {
        if(this == destinationTeleport || destinationTeleport == null) return;
        this.destinationTeleport = destinationTeleport;
        if(this.destinationTeleport.getDestination() == this) return;
        this.destinationTeleport.setDestinationTeleport(this);
    }

    public void teleportPlayer(Player player){
        if(player == null || destinationTeleport == null) return;
        int centerTeleportX = destinationTeleport.getPosX() + destinationTeleport.getWidth() / 2;
        int centerTeleportY = destinationTeleport.getPosY()  + destinationTeleport.getHeight() / 2;
        player.setPosition(centerTeleportX - player.getWidth()/2, centerTeleportY -  player.getHeight()/2);
        destinationTeleport.setTeleported(true);

        new When<>(
            () -> {
                return !destinationTeleport.intersects(player);
            },
            new Invoke<>(() -> {
                destinationTeleport.setTeleported(false);
            })
        ).scheduleFor(this);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new Loop<>(
            new Invoke<>(() -> {
                if(isTeleported()) return;

                Player player = scene.getFirstActorByType(Player.class);
                List<Actor> actors = scene.getActors();
                if(player == null || !actors.contains(this) || !actors.contains(destinationTeleport)) return;

                Rectangle2D playerCenter = new Rectangle2D.Double(player.getPosX() + (double) player.getWidth() / 2 - 1, player.getPosY() + (double) player.getHeight() /2 -1, 1, 1);
                Rectangle2D teleportArea = new Rectangle2D.Double(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());

                if(teleportArea.intersects(playerCenter)){
                    teleportPlayer(player);
                }
            })
        ).scheduleFor(this);
    }

    public boolean isTeleported() {
        return teleported;
    }

    public void setTeleported(boolean teleported) {
        this.teleported = teleported;
    }
}
