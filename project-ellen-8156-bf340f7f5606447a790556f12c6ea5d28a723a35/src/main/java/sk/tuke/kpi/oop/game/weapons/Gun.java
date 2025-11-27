package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm{

    public Gun(int countAmmo) {
        super(countAmmo);
    }

    public Gun(int countAmmo, int countAmmoMax) {
        super(countAmmo, countAmmoMax);
    }



    @Override
    protected Fireable createBullet() {
        return new  Bullet();
    }
}
