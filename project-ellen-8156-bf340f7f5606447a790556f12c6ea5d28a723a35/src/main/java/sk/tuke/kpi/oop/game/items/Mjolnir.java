package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.oop.game.Repairable;

public class Mjolnir  extends Hammer {
    public Mjolnir() {
        super(4);
    }

    @Override
    public void useWith(Repairable actor) {
        super.useWith(actor);
        System.out.println("Hammer was a Mjolnir");
    }
}
