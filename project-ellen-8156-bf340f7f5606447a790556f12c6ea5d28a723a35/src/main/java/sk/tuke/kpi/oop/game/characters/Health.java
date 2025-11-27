package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health  {
    private int minHealth;
    private int maxHealth;
//    private boolean healthIsZero = false;
    private List<FatigueEffect>  fatigueEffects = new ArrayList<>();

    public void onFatigued(FatigueEffect effect){
        fatigueEffects.add(effect);
        System.out.println(effect);

    }
    @FunctionalInterface
    public interface FatigueEffect {
        void apply();
    }

    public Health(int minHealth, int maxHealth) {
        this.minHealth = minHealth;
        this.maxHealth = maxHealth;
    }
    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getValue() {
        return minHealth;
    }
//    public int setVaLue(int value) {
//        this.minHealth = value;
//    }
    public void refill(int amount){
        if(amount > maxHealth) return;
        if(minHealth + amount > maxHealth){
            minHealth = maxHealth;
        } else {
            minHealth += amount;
        }

    }
    public void restore(){
        minHealth = maxHealth;
    }
    public void drain(int amount){
        if(minHealth - amount <= 0){
            minHealth = 0;
            fatigueEffects.forEach(FatigueEffect::apply);
            return;
        }
        minHealth -= amount;
    }
    public void exhaust(){
        minHealth = 0;
    }



}
