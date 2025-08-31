package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.oop.game.Cooler;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.tools.FireExtinguisher;
import sk.tuke.kpi.oop.game.tools.Hammer;

public class TrainingGameplay extends Scenario implements SceneListener{
    @Override
    public void setupPlay(@NotNull Scene scene) {
        Reactor reactor = new Reactor();
        Cooler cooler = new Cooler(reactor);
        Hammer hammer = new Hammer();
        FireExtinguisher FireExtinguisher = new FireExtinguisher();

        scene.addActor(reactor, 64, 64);
//        reactor.turnOn();

        scene.addActor(cooler, 0, 0);
        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(cooler::turnOn)
        ).scheduleFor(cooler);

        new When<>(
            () -> {
                return reactor.getTemperature() >= 3000;
            },
            new Invoke<>(() -> {
                hammer.useWith(reactor);
            })
        ).scheduleFor(reactor);

        new When<>(
            () -> {
                return reactor.getTemperature() >= 6000;
            },
            new Invoke<>(() -> {
                FireExtinguisher.useWith(reactor);
            })
        ).scheduleFor(reactor);
    }
}
