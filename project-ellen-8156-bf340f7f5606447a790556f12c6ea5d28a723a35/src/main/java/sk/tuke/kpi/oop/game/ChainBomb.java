package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;

import java.util.List;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.stream.Collectors;

public class ChainBomb extends TimeBomb{

    public ChainBomb(float time) {
        super(time);
    }

    @Override
    public void extraAction() {
        Scene scene = getScene();
        if (scene == null) return;

        Ellipse2D explosion = new Ellipse2D.Float(getPosX() - 50 + getWidth()/2, getPosY() - 50 + getHeight()/2 ,100, 100);

        List<ChainBomb> bombs = scene.getActors()
            .stream()
            .filter(actor -> actor instanceof ChainBomb)
            .map(actor -> (ChainBomb) actor)
            .collect(Collectors.toList());

        bombs.forEach(bomb -> {
            Rectangle2D bombArea = new Rectangle2D.Double(bomb.getPosX(), bomb.getPosY(), bomb.getWidth(), bomb.getHeight());
            if(explosion.intersects(bombArea)){
                bomb.activate();
            }
        });

    }

}
