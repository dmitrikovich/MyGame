package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl2.Lwjgl2Backend;
import sk.tuke.kpi.oop.game.scenarios.FirstSteps;
import sk.tuke.kpi.oop.game.scenarios.MissionImpossible;

public class Main {
    public static void main(String[] args) {
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);

        Game game = new GameApplication(windowSetup, new Lwjgl2Backend());
        Scene scene = new World("newWorld", "maps/mission-impossible.tmx",  new MissionImpossible.Factory());
        game.addScene(scene);

        MissionImpossible missionImpossible = new MissionImpossible();
        scene.addListener(missionImpossible);

        game.start();
        game.getInput().onKeyPressed(Input.Key.ESCAPE, game::stop);
    }

}
