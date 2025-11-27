package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

import java.util.List;

public class Alien extends AbstractActor implements Movable, Enemy, Alive {
    public static final Topic<Alien> ACTOR_ADDED_TOPIC = Topic.create("alien added", Alien.class);
    private Behaviour<? super Alien> behaviour;
    private Health health;
    Animation alienAnimation;
    public Alien() {
         alienAnimation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP);
         setAnimation(alienAnimation);
         health  = new Health(100, 100);
    }

    public Alien(Behaviour<? super Alien> behaviour) {
        alienAnimation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP);
        setAnimation(alienAnimation);
        this.behaviour = behaviour;
        health = new Health(100, 100);
    }

    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        if(scene == null) return;
       /* new When<>(
            () -> scene.getFirstActorByType(Actor.class) != null,
            new Invoke<>(() -> {
                Actor actor = scene.getFirstActorByType(Actor.class);
                if(actor instanceof Alive){
                    this.ripleyDamage();
                }
            })
        ).scheduleFor(this);*/

        new Loop<>(
            new ActionSequence<>(
                new Invoke<>(this::ripleyDamage),
                new Wait<>(0.5f)
            )
        ).scheduleFor(this);
        health.onFatigued(() -> getScene().removeActor(this));

        if(behaviour == null) return;
        else{
            behaviour.setUp(this);
        }

    }
    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    private void ripleyDamage(){
        Scene scene = getScene();
        List<Actor> actors = scene.getActors();
        for (Actor actor : actors) {
            if(actor instanceof Alive){
                if(this.intersects(actor) && !(actor instanceof Enemy)){
                    ((Alive) actor).getHealth().drain(5);
                }
            }
        }

    }
}
