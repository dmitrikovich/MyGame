package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class SmartCooler extends Cooler{

    private Reactor reactor;

    public SmartCooler(Reactor reactor) {
        super(reactor);
        this.reactor = reactor;
    }

    @Override
    public void coolReactor(){
        if(reactor == null) return;

        if (reactor.getTemperature() >= 2500 ){
            turnOn();
        }
        else if(reactor.getTemperature() <= 1500){
            turnOff();
        }
        if(this.isOn()) reactor.decreaseTemperature(1);
    }
}
