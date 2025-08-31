package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl2.Lwjgl2Backend;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Main {
    public static void main(String[] args) {
        // nastavenie okna hry: nazov okna a jeho rozmery
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);

        // vytvorenie instancie hernej aplikacie

        // pouzijeme implementaciu rozhrania `Game` triedou `GameApplication`
        Game game = new GameApplication(windowSetup, new Lwjgl2Backend());  // v pripade Mac OS bude druhy parameter "new Lwjgl2Backend()"

        // vytvorenie sceny pre hru

        // pouzijeme implementaciu rozhrania `Scene` triedou `World`
        Scene scene = new World("world");

        // pridanie sceny do hry

        game.addScene(scene);
        // spustenie hry
        Ripley ripley = new Ripley();
        game.start();

        scene.addActor(ripley, 100, 100);

        game.getInput().onKeyPressed(Input.Key.ESCAPE, game::stop);
    }
}
