package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class DefectiveLight extends Light implements Repairable {
    private Disposable loop;
    private boolean repaired;

    public DefectiveLight(){
        super();
        repaired = false;
    }

    public void defectToggle(){
        int randomNumber = (int) (Math.random() * 20);
        if(randomNumber == 1) super.toggle();
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        loop = new Loop<>(new Invoke<>(this::defectToggle)).scheduleFor(this);
    }

    @Override
    public boolean repair() {
        if(repaired) return false;
        new ActionSequence<>(
            new Invoke<>(() -> {
                loop.dispose();
                this.turnOn();
                repaired = true;
            }),
            new Wait<>(10),
            new Invoke<>(() -> {
                loop = new Loop<>(new Invoke<>(this::defectToggle)).scheduleFor(this);
                repaired = false;
            })
        ).scheduleFor(this);
        return true;
    }
}
