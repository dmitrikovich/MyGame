package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm{
    private int countAmmo;
    private int maxCountAmmo;
    public Firearm(int countAmmo, int countAmmoMax){
        this.countAmmo = countAmmo;
        this.maxCountAmmo = countAmmoMax;
    }
    public Firearm(int countAmmo){
        this.countAmmo = countAmmo;
        this.maxCountAmmo = countAmmo;
    }
    public int getMaxAmmo(){
        return maxCountAmmo;
    }
    public int getAmmo(){
        return countAmmo;
    }
    public void reload(int newAmmo){
        if(this.countAmmo + newAmmo > maxCountAmmo ){
            this.countAmmo = maxCountAmmo;
        } else{
            this.countAmmo += newAmmo;
        }
    }

    public Fireable fire(){
        if(this.countAmmo > 0){
            this.countAmmo--;
            return createBullet();
        }
        return null;
    }

    protected abstract Fireable createBullet();

}

